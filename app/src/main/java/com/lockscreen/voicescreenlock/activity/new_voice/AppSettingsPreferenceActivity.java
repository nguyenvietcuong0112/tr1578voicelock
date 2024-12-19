package com.lockscreen.voicescreenlock.activity.new_voice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.utils.StartStopLockService;


public class AppSettingsPreferenceActivity extends AppCompatActivity {
    public static final String TAG = "AppSettings P A";

    private ImageView back_button;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_settings);

        this.back_button = (ImageView) findViewById(R.id.back_button);
        this.back_button.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.preference_root, new Setting_Preference_Fragment(), "setting_preference_fragment");
        beginTransaction.commit();
    }


    public static class Setting_Preference_Fragment extends Fragment {

        private CheckBox cbLockService;
        private CheckBox cbDatetime;
        private CheckBox cbSound;
        private CheckBox cbVibration;

        private SharedPreferences sharedPreferences;
        private StartStopLockService startStopLockService = new StartStopLockService();

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Inflate the custom layout
            return inflater.inflate(R.layout.fragment_settings, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Initialize SharedPreferences
            sharedPreferences = requireContext().getSharedPreferences("voice_recognition_preference", Context.MODE_PRIVATE);

            // Find CheckBox views
            cbLockService = view.findViewById(R.id.cb_lock_service);
            cbDatetime = view.findViewById(R.id.cb_datetime);
            cbSound = view.findViewById(R.id.cb_sound);
            cbVibration = view.findViewById(R.id.cb_vibration);

            // Set initial states from SharedPreferences
            cbLockService.setChecked(sharedPreferences.getBoolean("lock_service", true));
            cbDatetime.setChecked(sharedPreferences.getBoolean("voice_lock_date_time", true));
            cbSound.setChecked(sharedPreferences.getBoolean("sound_flag", true));
            cbVibration.setChecked(sharedPreferences.getBoolean("vibration_flag", true));

            // Set listeners
            cbLockService.setOnCheckedChangeListener((buttonView, isChecked) -> {
                sharedPreferences.edit().putBoolean("lock_service", isChecked).apply();
                if (isChecked) {
                    startStopLockService.startServiceForAllAndroidAPILevel(requireContext());
                    Log.e("Settings", "Lock Service Started");
                } else {
                    startStopLockService.stopServiceInAllAPILevel(requireContext());
                    Log.e("Settings", "Lock Service Stopped");
                }
            });

            cbDatetime.setOnCheckedChangeListener((buttonView, isChecked) -> {
                sharedPreferences.edit().putBoolean("voice_lock_date_time", isChecked).apply();
                Log.e("Settings", "Date Time Show/Hide: " + isChecked);
            });

            cbSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
                sharedPreferences.edit().putBoolean("sound_flag", isChecked).apply();
                Log.e("Settings", "Sound Enabled/Disabled: " + isChecked);
            });

            cbVibration.setOnCheckedChangeListener((buttonView, isChecked) -> {
                sharedPreferences.edit().putBoolean("vibration_flag", isChecked).apply();
                Log.e("Settings", "Vibration Enabled/Disabled: " + isChecked);
            });
        }
    }
}
