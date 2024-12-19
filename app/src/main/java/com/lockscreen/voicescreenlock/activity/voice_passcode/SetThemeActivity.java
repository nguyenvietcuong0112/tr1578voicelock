package com.lockscreen.voicescreenlock.activity.voice_passcode;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.adapter.ThemeAdapter;

import java.util.Arrays;
import java.util.List;

public class SetThemeActivity extends AppCompatActivity {
    private int themeId;
    private ImageView btnSet;
    private ImageView bg;
    private List<Integer> themeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_theme);
        themeId = getIntent().getIntExtra("themeId", -1);

        themeList = Arrays.asList(
                R.drawable.bg0,
                R.drawable.bg1,
                R.drawable.bg2,
                R.drawable.bg3,
                R.drawable.bg4
        );

        btnSet = findViewById(R.id.btnSet);
        bg = findViewById(R.id.bg_item);
        bg.setImageResource(themeList.get(themeId));

        btnSet.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("selectedTheme", themeId);
            editor.apply();

            finish();
        });
    }
}