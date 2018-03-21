package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.helper.AppCompatPreferenceActivity;

/**
 * Created by 22278 on 2018/3/21.
 */

public class SystemSettingsActivity extends AppCompatPreferenceActivity {
    private static ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Button button=(Button)findViewById(R.id.button_log_out);
        if(button!=null){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("log_out",1);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.system_settings_list,target);
        setContentView(R.layout.button_log_out);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    public static class NewMessagesSettings extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_new_message);

            actionBar.setTitle("新消息提醒");
        }
    }

    public static class UniversalSettings extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_universal);

            actionBar.setTitle("通用设置");
        }
    }

    public static class PasswordSettings extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_password);

            actionBar.setTitle("密码设置");
        }
    }

    public static class UpdateSettings extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_update);

            actionBar.setTitle("版本更新");
        }
    }

    public static class VersionSettings extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_version);

            actionBar.setTitle("版本信息");
        }
    }

    public static class FeedbackSettings extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_feedback);

            actionBar.setTitle("反馈");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
                //点击返回键返回信息给上个活动
                Intent intent=new Intent();
                intent.putExtra("log_out",0);
                setResult(RESULT_OK,intent);
                finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("log_out",0);
        setResult(RESULT_OK,intent);
        finish();
    }
}
