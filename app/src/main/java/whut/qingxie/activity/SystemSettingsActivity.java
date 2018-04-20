package whut.qingxie.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.helper.AppCompatPreferenceActivity;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

/**
 * 位于MainActivity右上角
 * 系统设置页面
 * todo Android7.0之后有bug
 */

public class SystemSettingsActivity extends AppCompatPreferenceActivity {
    private static ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = getSupportActionBar();
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.toolbar_background);
        actionBar.setBackgroundDrawable(drawable);
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Button button = (Button) findViewById(R.id.button_log_out);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SystemSettingsActivity.this, MainActivity.class);
                    //状态设置为未登录
                    Content.setFLAG(-1);
                    OkhttpUtil.okHttpDelete("/user/logout", new CallBackUtil.CallBackMsg() {
                        @Override
                        public void onFailure(Call call, Exception e) {
                            Toast.makeText(SystemSettingsActivity.this, "注销失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Msg response) {
                            Toast.makeText(SystemSettingsActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, Content.getUserId() + new Date().toString() + "注销");
                        }
                    });
                    MainActivity.activity.finish();
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.system_settings_list, target);
        setContentView(R.layout.button_log_out);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    public static class NewMessagesSettings extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_new_message);

            actionBar.setTitle("新消息提醒");
        }
    }

    public static class UniversalSettings extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_universal);

            actionBar.setTitle("通用设置");
        }
    }

    public static class PasswordSettings extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_password);

            actionBar.setTitle("密码设置");
        }
    }

    public static class UpdateSettings extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_update);

            actionBar.setTitle("版本更新");
        }
    }

    public static class VersionSettings extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_version);

            actionBar.setTitle("版本信息");
        }
    }

    public static class FeedbackSettings extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_feedback);

            actionBar.setTitle("反馈");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
