package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.Item.MyHoursItem;
import whut.qingxie.R;
import whut.qingxie.adapter.ManageWorkTimeItemAdapter;
import whut.qingxie.Item.ManageWorkTimeItem;

/**
 * WorkerWorkFragment页面第二个item
 * 管理工时信息页面
 */
public class ManageWorkTimeActivity extends AppCompatActivity {
    private List<ManageWorkTimeItem> manageWorkTimeItemList=new ArrayList<>();

    private static ManageWorkTimeItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_work_time);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.manage_work_time_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new ManageWorkTimeItemAdapter(manageWorkTimeItemList);
        recyclerView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_manage_work_time);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //refresh监听
        smartRefreshLayout=(SmartRefreshLayout)findViewById(R.id.manage_worker_time_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                init();
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for(int i=0;i<5;i++){
                    manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
                }
                //结束加载更多
                if(manageWorkTimeItemList.size()<10)
                    smartRefreshLayout.finishLoadmore();
                else
                    smartRefreshLayout.finishLoadmoreWithNoMoreData();
                reFresh();
            }
        });

        //初始化数据
        if(manageWorkTimeItemList.size()==0)
            init();
    }

    private static void reFresh(){
        adapter.notifyDataSetChanged();
    }

    private void init(){
        manageWorkTimeItemList.clear();
        for(int i=0;i<5;i++){
            manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        }
        reFresh();
        smartRefreshLayout.resetNoMoreData();
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
