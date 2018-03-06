package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.SystemSettingsItem;
import whut.qingxie.adapter.SystemSettingsItemAdapter;

public class SystemSettingsActivity extends AppCompatActivity {

    private List<SystemSettingsItem> systemSettingsItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemseetings);

        //创建ListView
        if(systemSettingsItems.size()==0)
            initItems();
        SystemSettingsItemAdapter adapter=new SystemSettingsItemAdapter(SystemSettingsActivity.this,
                R.layout.system_settings_item,systemSettingsItems);
        ListView listView=(ListView)findViewById(R.id.system_list_view);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_system);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        systemSettingsItems.add(new SystemSettingsItem("新消息通知"));
        systemSettingsItems.add(new SystemSettingsItem("通用设置"));
        systemSettingsItems.add(new SystemSettingsItem("密码设置"));
        systemSettingsItems.add(new SystemSettingsItem("检测更新"));
        systemSettingsItems.add(new SystemSettingsItem("版本说明"));
        systemSettingsItems.add(new SystemSettingsItem("反馈"));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
