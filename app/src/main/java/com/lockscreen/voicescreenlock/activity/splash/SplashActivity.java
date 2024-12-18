package com.lockscreen.voicescreenlock.activity.splash;

import android.content.Intent;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.activity.language.LanguageActivity;
import com.lockscreen.voicescreenlock.base.BaseActivity;
import com.lockscreen.voicescreenlock.databinding.ActivitySplashBinding;
import com.lockscreen.voicescreenlock.utils.Constant;
import com.lockscreen.voicescreenlock.utils.SharePreferenceUtils;
import com.lockscreen.voicescreenlock.utils.SystemConfiguration;
import com.lockscreen.voicescreenlock.utils.SystemUtil;
import com.mallegan.ads.callback.InterCallback;
import com.mallegan.ads.util.Admob;
import com.mallegan.ads.util.ConsentHelper;

import java.util.Map;

public class SplashActivity extends BaseActivity {
    private InterCallback interCallback;
    boolean isInitializationCompleted;

    @Override
    public void bind() {
        SystemUtil.setLocale(this);
        this.isInitializationCompleted = getSharedPreferences("INITIALIZE", 0).getBoolean("initialization_success", true);

        SystemConfiguration.setStatusBarColor(this, R.color.white, SystemConfiguration.IconColor.ICON_DARK);
        ActivitySplashBinding activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(activitySplashBinding.getRoot());
        loadAds();
    }

    private void loadAds() {
        interCallback = new InterCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                startActivity(new Intent(SplashActivity.this, LanguageActivity.class));
                finish();
            }
        };
        if (SharePreferenceUtils.isOrganic(this)) {
            AppsFlyerLib.getInstance().registerConversionListener(this, new AppsFlyerConversionListener() {

                @Override
                public void onConversionDataSuccess(Map<String, Object> conversionData) {
                    String mediaSource = (String) conversionData.get("media_source");
                    SharePreferenceUtils.setOrganicValue(getApplicationContext(), mediaSource == null || mediaSource.isEmpty() || mediaSource.equals("organic"));
                }

                @Override
                public void onConversionDataFail(String s) {
                    // Handle conversion data failure
                }

                @Override
                public void onAppOpenAttribution(Map<String, String> map) {
                    // Handle app open attribution
                }

                @Override
                public void onAttributionFailure(String s) {
                    // Handle attribution failure
                }
            });
        }

        ConsentHelper consentHelper = ConsentHelper.getInstance(this);
        if (!consentHelper.canLoadAndShowAds()) {
            consentHelper.reset();
        }
        consentHelper.obtainConsentAndShow(this, () -> {
            Admob.getInstance().loadSplashInterAds2(SplashActivity.this, getString(R.string.inter_splash), 3000, interCallback);
            Constant.loadNativeLanguageSelect(SplashActivity.this);
        });
    }



}
