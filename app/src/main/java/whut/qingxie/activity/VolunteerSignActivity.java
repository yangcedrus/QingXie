package whut.qingxie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.VolunteerSignItemAdapter;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.entity.user.User;

public class VolunteerSignActivity extends AppCompatActivity {
    private List<User> volunteerList=new ArrayList<>();
    private VolunteerSignItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_sign);

        Intent intent=new Intent();
        Integer activityId=intent.getIntExtra("activityID",-1);
        if (volunteerList.size() == 0)
            initItems(activityId);
        //创建ListView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vol_sign_recyclerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new VolunteerSignItemAdapter(volunteerList);
        recyclerView.setAdapter(adapter);

        //负责人信息填写
        TextView title=(TextView)findViewById(R.id.volunteer_sign_title);
        TextView manager=(TextView)findViewById(R.id.volunteer_sign_manager);
        title.setText("拯救南湖大草原");
        manager.setText("王潇潇");

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vol_sign);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    //FIXME 获取服务器数据
    private void initItems(Integer activityID){
        volunteerList.clear();
        for(int i=0;i<10;i++){
            User user=new User();
            user.setName("王潇潇");
            volunteerList.add(user);
        }
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
