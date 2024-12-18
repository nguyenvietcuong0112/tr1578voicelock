package com.lockscreen.voicescreenlock.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.lockscreen.voicescreenlock.activity.new_voice.HomeActivityVoice;
import com.lockscreen.voicescreenlock.jobService.LockScreenForegroundService;


public class StartStopLockService {
    public void stopServiceInAllAPILevel(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.e(HomeActivityVoice.TAG, " : stopForegroundService called");
            context.stopService(new Intent(context, LockScreenForegroundService.class));
        } else {
            Log.e(HomeActivityVoice.TAG, " : stopService called");
            context.stopService(new Intent(context, LockScreenForegroundService.class));
        }
        context.getSharedPreferences("voice_recognition_preference", 0).edit().putBoolean("lock_service", false).apply();
    }

    public void startServiceForAllAndroidAPILevel(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.e(HomeActivityVoice.TAG, " : startForegroundService called");
            context.startForegroundService(new Intent(context, LockScreenForegroundService.class));
        } else {
            Log.e(HomeActivityVoice.TAG, " : startService called");
            context.startService(new Intent(context, LockScreenForegroundService.class));
        }
        context.getSharedPreferences("voice_recognition_preference", 0).edit().putBoolean("lock_service", true).apply();
    }
}
