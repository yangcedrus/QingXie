package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.adapter.ServiceItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static whut.qingxie.fragment.HomeFragment.reFresh;

/**
 * WorkerMeFragment第五个item
 * 我的志愿工作界面
 */
public class MyWorkActivity extends AppCompatActivity {
    private static SmartRefreshLayout smartRefreshLayout;
    private static ServiceItemAdapter adapter;
    private List<Activity4User> myWorkItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_work);

        //refresh监听
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.my_work_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initItems();
            }
        });

        if (myWorkItemList.size() == 0)
            initItems();

        //创建ListView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_work_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ServiceItemAdapter(myWorkItemList);
        recyclerView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my_work);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems() {
        myWorkItemList.clear();
        OkhttpUtil.okHttpGet("/activity/" + Content.getUserId() + "/works", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(MyWorkActivity.this, "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                Log.e("HomeFragment", "onFailure: " + e.getMessage());
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onResponse(Msg response) {
                List<Activity4User> activityInfo = (List<Activity4User>) response.getData().get("UserActivityList");

                myWorkItemList.addAll(activityInfo);
                //结束刷新
                smartRefreshLayout.finishRefresh();
                adapter.notifyDataSetChanged();
                reFresh();
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
