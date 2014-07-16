package com.github.destinyd.menudrawer.common;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;

import java.lang.reflect.Field;

/**
 * Created by dd on 14-7-16.
 */
public class SystemWindow {
    public static int get_statusbar_height(Context context) {
        Class c;
        try {
            c =
                    Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int y = context.getResources().getDimensionPixelSize(x);
            return y;
        } catch (Exception e) {
        }
        return 0;
    }

    public static int get_actionbar_height(Context context) {
        TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } else if (context.getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }
}
