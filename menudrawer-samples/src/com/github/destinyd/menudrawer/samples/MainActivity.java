package com.github.destinyd.menudrawer.samples;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(
                new SimpleAdapter(
                        this, getData(), android.R.layout.simple_list_item_1, new String[]{"title"},
                        new int[]{android.R.id.text1}
                )
        );
        getListView().setScrollbarFadingEnabled(false);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
        Intent intent = new Intent(this, (Class<? extends Activity>) map.get("activity"));
        startActivity(intent);
    }

    private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        addItem(data, "simple", SimpleActivity.class);
        addItem(data, "sherlock", SimpleSherlockActivity.class);
        addItem(data, "functions", SimpleFragment.class);
        addItem(data, "sherlock functions", SherlockFragment.class);
        addItem(data, "set_foreground_opening_offset(10)", SimpleOffsetActivity.class);
        addItem(data, "set_foreground_opening_offset_px(10)", SimpleOffsetPxActivity.class);
        addItem(data, "custom actionbar", CustomActionbarActivity.class);

        return data;
    }

    private void addItem(List<Map<String, Object>> data, String title,
                         Class<? extends Activity> activityClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", data.size() + ". " + title);
        map.put("activity", activityClass);
        data.add(map);
    }
}
