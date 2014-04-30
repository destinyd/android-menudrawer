package net.simonvt.menudrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockActivity;

public class ActivityBase extends SherlockActivity implements Button.OnClickListener {

    protected MenuDrawer mMenuDrawer;
    private int foreground_opening_offset_dp = 5;
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
        mMenuDrawer = MenuDrawer.attach(this, Position.TOP);
        mMenuDrawer.setMenuView(R.layout.menu);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN); //全屏拖动， TOUCH_MODE_BEZEL为边缘拖动
//        mMenuDrawer.setDrawerIndicatorEnabled(true);
        setMenuBtnsClick();

        setMenuSize();
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
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, Activity1.class));
                mMenuDrawer.closeMenu();
                break;

            case R.id.button2:
                startActivity(new Intent(this, Activity2.class));
                mMenuDrawer.closeMenu();
                break;

            case R.id.button3:
                startActivity(new Intent(this, Activity3.class));
                mMenuDrawer.closeMenu();
                break;

            case R.id.button4:
                startActivity(new Intent(this, Activity4.class));
                mMenuDrawer.closeMenu();
                break;
        }
    }
    public void set_foreground_opening_offset(int offset_dp){
        foreground_opening_offset_dp = offset_dp;
    };

    private void setMenuSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int displayHeight = metrics.heightPixels;

        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else if (getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
//        mMenuDrawer.setDropShadowEnabled(false);

        setMenuSize(displayHeight - actionBarHeight - mMenuDrawer.getTouchBezelSize());
    }

    private void setMenuSize(int pHeight){
        int height = pHeight - mMenuDrawer.dpToPx(foreground_opening_offset_dp);// - mMenuDrawer.getTouchBezelSize()
        mMenuDrawer.setMenuSize(height);
    }

}

