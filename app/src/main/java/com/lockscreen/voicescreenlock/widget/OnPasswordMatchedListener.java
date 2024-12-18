package com.lockscreen.voicescreenlock.widget;


public interface OnPasswordMatchedListener {
    void onPINPasswordMatched();

    void onVoicePasswordMatched();

    void onVoicePasswordNotMatched();
}
