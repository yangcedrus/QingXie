package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

public class SystemSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_settings);

        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.sys_layout1);
        RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.sys_layout2);
        RelativeLayout layout3 = (RelativeLayout) findViewById(R.id.sys_layout3);
        RelativeLayout layout4 = (RelativeLayout) findViewById(R.id.sys_layout4);
        RelativeLayout layout5 = (RelativeLayout) findViewById(R.id.sys_layout5);
        RelativeLayout layout6 = (RelativeLayout) findViewById(R.id.sys_layout6);
        RelativeLayout log_out = (RelativeLayout) findViewById(R.id.sys_log_out);

        layout1.setOnClickListener(new MyListener());
        layout2.setOnClickListener(new MyListener());
        layout3.setOnClickListener(new MyListener());
        layout4.setOnClickListener(new MyListener());
        layout5.setOnClickListener(new MyListener());
        layout6.setOnClickListener(new MyListener());
        log_out.setOnClickListener(new MyListener());

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sys_settings);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * 注销函数
     */
    private void log_out() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return true;
    }

    /**
     * 监听类
     */
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.sys_layout1:
                    intent = new Intent(SystemSettingsActivity.this, SettingPreference.class);
                    intent.putExtra("flag",1);
                    startActivity(intent);
                    break;
                case R.id.sys_layout2:
                    intent = new Intent(SystemSettingsActivity.this, SettingPreference.class);
                    intent.putExtra("flag",2);
                    startActivity(intent);
                    break;
                case R.id.sys_layout3:
                    intent = new Intent(SystemSettingsActivity.this, SettingPreference.class);
                    intent.putExtra("flag",3);
                    startActivity(intent);
                    break;
                case R.id.sys_layout4:
                    intent = new Intent(SystemSettingsActivity.this, SettingPreference.class);
                    intent.putExtra("flag",4);
                    startActivity(intent);
                    break;
                case R.id.sys_layout5:
                    intent = new Intent(SystemSettingsActivity.this, SettingPreference.class);
                    intent.putExtra("flag",5);
                    startActivity(intent);
                    break;
                case R.id.sys_layout6:
                    intent = new Intent(SystemSettingsActivity.this, SettingPreference.class);
                    intent.putExtra("flag",6);
                    startActivity(intent);
                    break;
                case R.id.sys_log_out:
                    log_out();
                    break;
            }
        }

    }
}
