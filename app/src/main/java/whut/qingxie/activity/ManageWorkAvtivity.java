package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.adapter.ManageVolunteerItemAdapter;
import whut.qingxie.adapter.ManageWorkItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

/**
 * WorkerWorkFragment页面第二个item
 * 志愿活动管理
 */
public class ManageWorkAvtivity extends AppCompatActivity {

    private List<Activity4User> activityInfoList=new ArrayList<>();
    private ManageWorkItemAdapter adapter;

    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_work);

        if(activityInfoList.size()==0)
            initItems();
        //创建ListView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.manage_work_recyclerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ManageWorkItemAdapter(activityInfoList);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout=(SmartRefreshLayout)findViewById(R.id.manage_work_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initItems();
                smartRefreshLayout.finishRefresh();
            }
        });

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_manage_work);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        OkhttpUtil.okHttpGet("/activity/" + Content.getUserId() + "/works", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.d("ManageWorkActivity", "onFailure: ");
            }

            @Override
            public void onResponse(Msg response) {
                List<Activity4User> list=(List<Activity4User>)response.getData().get("UserActivityList");
                activityInfoList.addAll(list);
                smartRefreshLayout.finishRefresh();
                adapter.notifyDataSetChanged();
            }
        });
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
