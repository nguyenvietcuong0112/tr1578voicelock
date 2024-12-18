package com.lockscreen.voicescreenlock.utils;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.lockscreen.voicescreenlock.R;


public class GradientTextView extends AppCompatTextView {
    public GradientTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public GradientTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        int startColor = ContextCompat.getColor(getContext(), R.color.color_FF9AE3);
        int endColor = ContextCompat.getColor(getContext(), R.color.color_2B65FF);
        setTextColor(startColor);
        Shader textShader = new LinearGradient(0, 0, getPaint().measureText(getText().toString()), getTextSize(),
                new int[]{startColor, endColor},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        getPaint().setShader(textShader);
    }
}
