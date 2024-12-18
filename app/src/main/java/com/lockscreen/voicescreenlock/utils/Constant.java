package com.lockscreen.voicescreenlock.utils;


import android.content.Context;
import android.media.MediaPlayer;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.nativead.NativeAd;
import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.model.LanguageModel;
import com.mallegan.ads.callback.NativeCallback;
import com.mallegan.ads.util.Admob;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static InterstitialAd interGetTwo = null;
    public static InterstitialAd interAlert = null;
    public static InterstitialAd interIntro = null;

    public static NativeAd nativeAdsLanguageSelect = null;


    public static void loadNativeLanguageSelect(Context context) {
        if (Constant.nativeAdsLanguageSelect == null) {
            Admob.getInstance().loadNativeAd(context, context.getString(R.string.native_language_select), new NativeCallback() {
                @Override
                public void onAdFailedToLoad() {
                    super.onAdFailedToLoad();
                    Constant.nativeAdsLanguageSelect = null;
                }

                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    super.onNativeAdLoaded(nativeAd);
                    Constant.nativeAdsLanguageSelect = nativeAd;
                }
            });
        }
    }

    public static ArrayList<LanguageModel> getLanguage() {
        ArrayList<LanguageModel> listLanguage = new ArrayList<>();
        listLanguage.add(new LanguageModel("English", "en", false, R.drawable.flag_en));
        listLanguage.add(new LanguageModel("Hindi", "hi", false, R.drawable.flag_hi));
        listLanguage.add(new LanguageModel("Spanish", "es", false, R.drawable.flag_es));
        listLanguage.add(new LanguageModel("French", "fr", false, R.drawable.flag_fr));
        listLanguage.add(new LanguageModel("German", "de", false, R.drawable.flag_de));
        listLanguage.add(new LanguageModel("Italia", "it", false, R.drawable.flag_italia));
        listLanguage.add(new LanguageModel("Portuguese", "pt", false, R.drawable.flag_portugese));
        listLanguage.add(new LanguageModel("Korea", "ko", false, R.drawable.flag_korea));
        return listLanguage;
    }

}
