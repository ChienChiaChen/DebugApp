package com.chiachen.debugapppractices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.chiachen.debugapppractices.utils.DeviceInfo;
import com.chiachen.debugapppractices.utils.ui.PreferenceView;

/**
 * Created by jianjiacheng on 21/12/2017.
 */

public class ExpertSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_expert);

        initUI();
    }

    private void initUI() {
        {
            LinearLayout list = findViewById(R.id.expert_mode_system_info_list);

            list.addView(new PreferenceView.Builder(this)
                    .setTitle(ExpertSettingHelper.ANDROID_OS)
                    .setValue(DeviceInfo.getOSVersion())
                    .build());

            list.addView(new PreferenceView.Builder(this)
                    .setTitle(ExpertSettingHelper.VENDOR)
                    .setValue(DeviceInfo.getVendor())
                    .build());

            list.addView(new PreferenceView.Builder(this)
                    .setTitle(ExpertSettingHelper.SYSTEM_COUNTRY)
                    .setValue(DeviceInfo.getLocale())
                    .build());
        }
    }
}
