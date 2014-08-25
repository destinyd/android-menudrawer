package com.github.destinyd.menudrawer;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import com.actionbarsherlock.app.ActionBar;
import com.github.destinyd.menudrawer.common.DisplayModule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dd on 14-7-10.
 */
public class KCVerticalDrawerHandler {
    private static final String TAG = "KCVerticalDrawerHandler";
    protected MenuDrawer mMenuDrawer;
    private Context context;
    private Activity activity;
    private int foreground_opening_offset_dp = 0;
    final private int actionBarHeight;
    final private int statusBarHeight;
    final private int statusBarHeightDip;
    final private int display_height;
    private int blank_height_dp = 0;
    private boolean GTE_HONEYCOMB = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    private int foreground_opening_offset = 0;

    public KCVerticalDrawerHandler(Context context) {
        this.context = context;
        activity = (Activity) context;

        display_height = DisplayModule.get_display_height(context);
        actionBarHeight = DisplayModule.get_actionbar_height(context);
        statusBarHeight = DisplayModule.get_statusbar_height(context);
        statusBarHeightDip = DisplayModule.px_to_dp(context, statusBarHeight);

        mMenuDrawer = MenuDrawer.attach(activity, MenuDrawer.Type.BEHIND, Position.TOP, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL); //TOUCH_MODE_FULLSCREEN为全屏拖动
        // TOUCH_MODE_BEZEL为边缘拖动(这个是拉多少放手就全出来)
        mMenuDrawer.setDropShadowEnabled(false);

        blank_height_dp = DisplayModule.px_to_dp(context, actionBarHeight);

//        set_foreground_opening_offset(blank_height_dp);
        set_foreground_opening_offset_px(actionBarHeight);
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

    public View get_background_view(){
        return mMenuDrawer.getMenuView();
    }

    public View get_foreground_view(){
        return mMenuDrawer.getContentContainer();
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
        set_foreground_opening_offset_px(DisplayModule.dp_to_px(context, offset_dp));
    }

    public void set_foreground_opening_offset_px(int offset_px){
        int size_touch_bezel;
        if (GTE_HONEYCOMB) {
            foreground_opening_offset = offset_px + statusBarHeight;
            size_touch_bezel = offset_px;
        }
        else{
            foreground_opening_offset = offset_px;
            size_touch_bezel = offset_px + statusBarHeight;
        }
        mMenuDrawer.setTouchBezelSize(size_touch_bezel);
        int height = display_height - foreground_opening_offset;// - mMenuDrawer.getTouchBezelSize()
        setMenuSize(height);
    }

    // 切换手势支持
    public void enable_gesture(boolean flag) {
        if (flag)
            mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
        else
            mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_NONE);
    }

    public void set_drop_shadow_enable(boolean enable){
        mMenuDrawer.setDropShadowEnabled(enable);
    }

    public void set_drop_shadow_enable(){
        set_drop_shadow_enable(true);
    }

    public void set_drop_shadow_color(int color){
        mMenuDrawer.setDropShadowColor(color);
    }

    public void set_drop_shadow_size_px(int size_px){
        mMenuDrawer.setDropShadowSize(size_px);
    }

    public boolean is_open(){
        return mMenuDrawer.getDrawerState() != MenuDrawer.STATE_CLOSED;
    }

    private void setMenuSize(int height) {
        mMenuDrawer.setMenuSize(height);
    }

}
