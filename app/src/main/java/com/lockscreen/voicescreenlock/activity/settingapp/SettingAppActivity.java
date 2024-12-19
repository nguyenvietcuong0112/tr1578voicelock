package com.lockscreen.voicescreenlock.activity.settingapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;


import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.activity.language.LanguageActivity;
import com.lockscreen.voicescreenlock.activity.new_voice.AppSettingsPreferenceActivity;
import com.lockscreen.voicescreenlock.base.BaseActivity;
import com.lockscreen.voicescreenlock.databinding.ActivitySettingScreenBinding;
import com.lockscreen.voicescreenlock.utils.SystemConfiguration;
import com.lockscreen.voicescreenlock.utils.SystemUtil;
import com.mallegan.ads.util.AppOpenManager;

import java.util.Timer;
import java.util.TimerTask;


public class SettingAppActivity extends BaseActivity {

    private ActivitySettingScreenBinding binding;
    private boolean isBtnProcessing = false;

    @Override
    public void bind() {
        SystemUtil.setLocale(this);
        SystemConfiguration.setStatusBarColor(this, R.color.mycolorwhite, SystemConfiguration.IconColor.ICON_DARK);
        binding = ActivitySettingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ivBackButton.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        binding.btnShare.setOnClickListener(v -> {
            if (isBtnProcessing) return;
            isBtnProcessing = true;
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "https://play.google.com/store/apps/details?id=" + getPackageName();;
            String sub = "Voice Lock";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
            myIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(myIntent, "Share"));
            AppOpenManager.getInstance().disableAppResumeWithActivity(SettingAppActivity.class);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isBtnProcessing = false;
                }
            }, 1000);
        });
        binding.btnLanguage.setOnClickListener(v -> startActivity(new Intent(SettingAppActivity.this, LanguageActivity.class)));
        binding.btnRateUs.setOnClickListener(v -> {
            Uri uri = Uri.parse("market://details?id=");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                this.startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                this.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=")));
            }
        });
        binding.btnPrivacyPolicy.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        binding.btnLockSetting.setOnClickListener(v -> startActivity(new Intent(SettingAppActivity.this, AppSettingsPreferenceActivity.class)));

    }

}
