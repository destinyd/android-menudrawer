package com.github.destinyd.menudrawer.samples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import com.github.destinyd.menudrawer.KCVerticalDrawerHandler;
import com.github.destinyd.menudrawer.common.DisplayModule;

/**
 * Created by dd on 14-7-16.
 */
public class CustomActionbarActivity extends Activity {
    KCVerticalDrawerHandler kcVerticalDrawerHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.NoTitleBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_actionbar);
        kcVerticalDrawerHandler = new KCVerticalDrawerHandler(this);
        kcVerticalDrawerHandler.add_background_view(R.layout.simple_menu);
        float actionbar_height = getResources().getDimension(R.dimen.custom_actionbar_height);
        kcVerticalDrawerHandler.set_foreground_opening_offset_px((int)actionbar_height);
    }
}