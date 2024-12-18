package com.lockscreen.voicescreenlock.jobService;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.IntentFilter;
import android.util.Log;

import com.lockscreen.voicescreenlock.receiver.ScreenOnOffReceiverListener;


public class LockScreenJobService extends JobService {
    ScreenOnOffReceiverListener screenOnOffReceiver;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e("xcxcxc", "Job Service Started");
        this.screenOnOffReceiver = new ScreenOnOffReceiverListener(getApplicationContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.screenOnOffReceiver, intentFilter);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e("xcxcxc", "Job Service Stopped");
        unregisterReceiver(this.screenOnOffReceiver);
        return true;
    }
}
