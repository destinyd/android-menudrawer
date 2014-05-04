package net.simonvt.menudrawer;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class MainActivity extends SherlockFragmentActivity implements Button.OnClickListener {

    private static final String TAG = "MainActivity";
    protected MenuDrawer mMenuDrawer;
    private int foreground_opening_offset_dp = 0;
    Fragment mCurrent = null;
    FragmentManager mFragmentMan;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.TOP, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN); //全屏拖动， TOUCH_MODE_BEZEL为边缘拖动
        mMenuDrawer.setMenuView(R.layout.menu);
        mMenuDrawer.setContentView(R.layout.activity_main);
        mMenuDrawer.setDropShadowEnabled(true);

        setMenuBtnsClick();

        setMenuSize();

        mFragmentMan = getSupportFragmentManager();

        FragmentTransaction transaction = mFragmentMan.beginTransaction()
                ;//.setCustomAnimations(android.R.anim.fade_out, R.anim.out_to_none1);
        mCurrent = new Fragment1();
        transaction.add(R.id.fl_main, mCurrent).commit();

    }


    private void setMenuBtnsClick() {
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction;
        switch (v.getId()) {
            case R.id.button1:
//                startActivity(new Intent(this, Activity1.class));
//                mMenuDrawer.closeMenu();
                set_foreground_opening_offset(0);
//                getSupportActionBar().hide();
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none);
                switchTo(transaction, new Fragment1());
                break;

            case R.id.button2:
//                startActivity(new Intent(this, Activity2.class));
//                mMenuDrawer.closeMenu();
                set_foreground_opening_offset(50);
//                getSupportActionBar().hide();
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none1);
                switchTo(transaction, new Fragment2());
                break;

            case R.id.button3:
//                startActivity(new Intent(this, Activity3.class));
//                mMenuDrawer.closeMenu();
                set_foreground_opening_offset(100);
//                getSupportActionBar().show();
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_top);
                switchTo(transaction, new Fragment3());
                break;

            case R.id.button4:
//                startActivity(new Intent(this, Activity4.class));
//                mMenuDrawer.closeMenu();
                set_foreground_opening_offset(1);
//                getSupportActionBar().show();
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none1);
                switchTo(transaction, new Fragment4());
                break;
        }
        mMenuDrawer.closeMenu();
    }

    public void set_foreground_opening_offset(int offset_dp) {
        foreground_opening_offset_dp = offset_dp;
        setMenuSize();
    }

    ;

    private void setMenuSize() {
//        set_foreground_opening_offset(50);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int displayHeight = metrics.heightPixels;
        Log.e(TAG, "displayHeight:" + displayHeight);

        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else if (getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        mMenuDrawer.setDropShadowEnabled(false);

        setMenuSize(displayHeight - actionBarHeight - mMenuDrawer.getTouchBezelSize());
    }

    private void setMenuSize(int pHeight) {
        int height = pHeight - mMenuDrawer.dpToPx(foreground_opening_offset_dp);// - mMenuDrawer.getTouchBezelSize()
        mMenuDrawer.setMenuSize(height);
    }

    public void switchContent(FragmentTransaction transaction, Fragment from, Fragment to) {
        if (mCurrent != to) {
            mCurrent = to;
//            FragmentTransaction transaction = mFragmentMan.beginTransaction().setCustomAnimations(
//                    R.anim.in_from_bottom, R.anim.out_to_none1);
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.fl_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    public void switchTo(FragmentTransaction transaction,Fragment to) {
        switchContent(transaction, mCurrent, to);
    }
}

