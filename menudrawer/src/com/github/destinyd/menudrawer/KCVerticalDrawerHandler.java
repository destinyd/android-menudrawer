package com.github.destinyd.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by dd on 14-7-10.
 */
public class KCVerticalDrawerHandler {
    private static final String TAG = "KCVerticalDrawerHandler";
    protected MenuDrawer mMenuDrawer;
    private Context context;
    private Activity activity;
    private int foreground_opening_offset_dp = 0;

    public KCVerticalDrawerHandler(Context context) {
        this.context = context;
        activity = (Activity) context;

        mMenuDrawer = MenuDrawer.attach(activity, MenuDrawer.Type.BEHIND, Position.TOP, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN); //全屏拖动， TOUCH_MODE_BEZEL为边缘拖动

        setMenuSize();
    }

    // 增加背景 View
    public void add_background_view(View view) {
        mMenuDrawer.setMenuView(view);
    }

    public void add_background_view(int view_res_id) {
        mMenuDrawer.setMenuView(view_res_id);
    }

    // 增加前景 View
    public void add_foreground_view(View view) {
        mMenuDrawer.setContentView(view);
    }

    // 增加前景 View
    public void add_foreground_view(int view_res_id) {
        mMenuDrawer.setContentView(view_res_id);
    }

    // 打开和关闭背景View
    public void open() {
        mMenuDrawer.openMenu(true);
    }

    // 打开和关闭背景View
    public void open(boolean animate) {
        mMenuDrawer.openMenu(animate);
    }

    public void close() {
        mMenuDrawer.closeMenu();
    }

    public void close(boolean animate) {
        mMenuDrawer.closeMenu(animate);
    }

    // 设置 open 时，前景向下滑动后保留的露出的部分高度，以dp为单位。
    public void set_foreground_opening_offset(int offset_dp) {
        foreground_opening_offset_dp = offset_dp;
        setMenuSize();
    }

    // 切换手势支持
    public void enable_gesture(boolean flag) {
        if (flag)
            mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        else
            mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_NONE);
    }

    private void setMenuSize() {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int displayHeight = metrics.heightPixels;

        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } else if (context.getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } else { // 2.x
            //不需要做任何处理
        }

        setMenuSize(displayHeight - actionBarHeight);
    }

    private void setMenuSize(int pHeight) {
        int height = pHeight - mMenuDrawer.dpToPx(foreground_opening_offset_dp);// - mMenuDrawer.getTouchBezelSize()
        mMenuDrawer.setMenuSize(height);
    }
}
