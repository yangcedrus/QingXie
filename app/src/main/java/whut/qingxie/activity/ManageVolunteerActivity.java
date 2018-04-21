package whut.qingxie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.ManageVolunteerItemAdapter;
import whut.qingxie.entity.activity.VolActivityInfo;

/**
 * WorkerWorkFragment第四个item
 * 志愿者管理页面
 */
public class ManageVolunteerActivity extends AppCompatActivity {
    private List<VolActivityInfo> activityInfoList=new ArrayList<>();
    private ManageVolunteerItemAdapter adapter;

    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_volunteer);

        if(activityInfoList.size()==0)
            initItems();
        //创建ListView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.manage_volunteer_recyclerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ManageVolunteerItemAdapter(activityInfoList);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout=(SmartRefreshLayout)findViewById(R.id.manage_volunteer_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initItems();
                smartRefreshLayout.finishRefresh();
            }
        });

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_manage_volunteer);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        for(int i=0;i<6;i++){
            VolActivityInfo activityInfo=new VolActivityInfo();
            activityInfo.setName("拯救南湖大草原");
            activityInfo.setManagerId(11111);
            activityInfoList.add(activityInfo);
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
