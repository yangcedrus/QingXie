package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.adapter.ServiceItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

/**
 * WorkerMeFragment,MeFragment第三个item
 * 我的志愿服务页面
 */
public class MyServiceActivity extends AppCompatActivity {

    private List<Activity4User> volServiceItemList =new ArrayList<>();

    private static SmartRefreshLayout smartRefreshLayout;
    private static ServiceItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        //refresh监听
        smartRefreshLayout=(SmartRefreshLayout)findViewById(R.id.service_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initItems();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishLoadmoreWithNoMoreData();
                reFresh();
            }
        });

        //创建ListView
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.service_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new ServiceItemAdapter(volServiceItemList);
        recyclerView.setAdapter(adapter);

        if(volServiceItemList.size()==0)
            initItems();

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_my_service);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        volServiceItemList.clear();
        OkhttpUtil.okHttpGet("/activity/" + Content.getUserId() + "/activities", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(MyServiceActivity.this,"连接超时，请检查网络连接",Toast.LENGTH_LONG).show();
                Log.e("HomeFragment", "onFailure: " + e.getMessage());
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onResponse(Msg response) {
                List<Activity4User> activityInfo=(List<Activity4User>) response.getData().get("UserActivityList");

                volServiceItemList.addAll(activityInfo);
                //结束刷新
                smartRefreshLayout.resetNoMoreData();
                smartRefreshLayout.finishRefresh();
                reFresh();
            }
        });
    }

    private static void reFresh(){
        adapter.notifyDataSetChanged();
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
