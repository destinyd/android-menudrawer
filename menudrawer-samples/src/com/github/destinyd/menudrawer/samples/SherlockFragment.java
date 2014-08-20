package com.github.destinyd.menudrawer.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.github.destinyd.menudrawer.KCVerticalDrawerHandler;

public class SherlockFragment extends SherlockFragmentActivity implements Button.OnClickListener {

    private static final String TAG = "SherlockActivity";
    Fragment mCurrent = null;
    FragmentManager mFragmentMan;
    KCVerticalDrawerHandler kcVerticalDrawerHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SherlockTheme);
        super.onCreate(savedInstanceState);

        kcVerticalDrawerHandler = new KCVerticalDrawerHandler(this);
        kcVerticalDrawerHandler.add_background_view(R.layout.menu);
        kcVerticalDrawerHandler.add_foreground_view(R.layout.activity_main);

        set_menu_buttons_click();

        mFragmentMan = getSupportFragmentManager();

        FragmentTransaction transaction = mFragmentMan.beginTransaction();//.setCustomAnimations(android.R.anim.fade_out, R.anim.out_to_none1);
        mCurrent = new Fragment1();
        transaction.add(R.id.fl_main, mCurrent).commit();
    }

    private void set_menu_buttons_click() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction;
        switch (v.getId()) {
            case R.id.button1:
                kcVerticalDrawerHandler.set_foreground_opening_offset(0);
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none);
                switch_to(transaction, new Fragment1());
                break;

            case R.id.button2:
                kcVerticalDrawerHandler.set_foreground_opening_offset(50);
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none1);
                switch_to(transaction, new Fragment2());
                break;

            case R.id.button3:
                kcVerticalDrawerHandler.set_foreground_opening_offset(100);
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_top);
                switch_to(transaction, new Fragment3());
                break;

            case R.id.button4:
                kcVerticalDrawerHandler.set_foreground_opening_offset(1);
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none1);
                switch_to(transaction, new Fragment4());
                break;

            case R.id.button5:
                kcVerticalDrawerHandler.enable_gesture(false);
                transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                        R.anim.in_from_bottom, R.anim.out_to_none1);
                switch_to(transaction, new Fragment5());
                break;
            case R.id.button6:
                kcVerticalDrawerHandler.set_foreground_opening_offset_px(100);
                break;
        }
        kcVerticalDrawerHandler.close();
    }

    public void switchContent(FragmentTransaction transaction, Fragment from, Fragment to) {
        if (mCurrent != to) {
            mCurrent = to;
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.fl_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    public void switch_to(FragmentTransaction transaction, Fragment to) {
        switchContent(transaction, mCurrent, to);
    }

    public void enable_gesture(View view){
        kcVerticalDrawerHandler.enable_gesture(true);
        TextView tv_enable_gesture_status = (TextView) findViewById(R.id.tv_enable_gesture_status);
        tv_enable_gesture_status.setText("enable_gesture(true) now");
    }
}

