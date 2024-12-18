package com.lockscreen.voicescreenlock.activity.voice_passcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.activity.new_voice.UpdateAlternatePinActivity;
import com.lockscreen.voicescreenlock.activity.new_voice.UpdateVoicePasswordActivity;

public class LockActivity extends AppCompatActivity  {


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_lock);

        ImageView btnVoice = (ImageView) findViewById(R.id.btnVoice);
        ImageView btnPin = (ImageView) findViewById(R.id.btnPin);
        ImageView btnBack = (ImageView) findViewById(R.id.back_button);

        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LockActivity.this, UpdateVoicePasswordActivity.class));

            }
        });


        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LockActivity.this, UpdateAlternatePinActivity.class));

            }
        });

        btnBack.setOnClickListener(v -> onBackPressed());

    }
}
