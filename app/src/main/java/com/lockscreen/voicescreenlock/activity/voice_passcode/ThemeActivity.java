package com.lockscreen.voicescreenlock.activity.voice_passcode;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.adapter.ThemeAdapter;

import java.util.Arrays;
import java.util.List;

public class ThemeActivity extends AppCompatActivity {
    private GridView gridView;
    private ThemeAdapter adapter;
    private List<Integer> themeList;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);
        ImageView back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        gridView = findViewById(R.id.gridView);


        themeList = Arrays.asList(
                R.drawable.bg0,
                R.drawable.bg1,
                R.drawable.bg2,
                R.drawable.bg3,
                R.drawable.bg4
        );

        // Adapter cho GridView
        adapter = new ThemeAdapter(this, themeList);
        gridView.setAdapter(adapter);

        preferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        int selectedTheme = preferences.getInt("selectedTheme", -1);

        if (selectedTheme != -1 && themeList.contains(selectedTheme)) {
            adapter.setSelectedPosition(themeList.indexOf(selectedTheme));
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                adapter.setSelectedPosition(position);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("selectedTheme", themeList.get(position));
                editor.apply();
            }
        });
    }
}