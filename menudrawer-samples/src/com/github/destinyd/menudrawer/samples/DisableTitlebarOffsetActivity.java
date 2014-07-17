package com.github.destinyd.menudrawer.samples;

import android.app.Activity;
import android.os.Bundle;
import com.github.destinyd.menudrawer.KCVerticalDrawerHandler;

/**
 * Created by dd on 14-7-16.
 */
public class DisableTitlebarOffsetActivity extends Activity {
    KCVerticalDrawerHandler kcVerticalDrawerHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
        kcVerticalDrawerHandler = new KCVerticalDrawerHandler(this);
        kcVerticalDrawerHandler.add_background_view(R.layout.simple_menu);
        kcVerticalDrawerHandler.enable_default_titlebar(false);
        kcVerticalDrawerHandler.set_foreground_opening_offset(10);
    }
}