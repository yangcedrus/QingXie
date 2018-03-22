package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.ServiceItemAdapter;
import whut.qingxie.entity.activity.VolActivityInfo;

public class MyServiceActivity extends AppCompatActivity {

    private List<VolActivityInfo> volServiceItemList =new ArrayList<>();

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
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for(int i=0;i<5;i++){
                    volServiceItemList.add(new VolActivityInfo(1,"敬老院活动",1,
                            "2",0,4.0,2.0,10,
                            "东院敬老院","东院敬老院活动，打扫卫生","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
                }
                //结束加载更多
                if(volServiceItemList.size()<10)
                    smartRefreshLayout.finishLoadmore();
                else
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
        for(int i=0;i<5;i++){
            volServiceItemList.add(new VolActivityInfo(1,"敬老院活动",1,
                    "2",0,4.0,2.0,10,
                    "东院敬老院","东院敬老院活动，打扫卫生",null));
        }
        reFresh();
        smartRefreshLayout.resetNoMoreData();
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
