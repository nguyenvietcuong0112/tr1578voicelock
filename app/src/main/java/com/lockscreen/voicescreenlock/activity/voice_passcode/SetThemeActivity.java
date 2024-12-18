package com.lockscreen.voicescreenlock.activity.voice_passcode;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.lockscreen.voicescreenlock.R;

public class SetThemeActivity extends AppCompatActivity {
    private int themeId;
    private Button btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_theme);

        btnSet = findViewById(R.id.btnSet);
        themeId = getIntent().getIntExtra("themeId", -1);

        btnSet.setOnClickListener(v -> {
            // Lưu themeId vào SharedPreferences
            SharedPreferences preferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("selectedTheme", themeId);
            editor.apply();

            finish();
        });
    }
}