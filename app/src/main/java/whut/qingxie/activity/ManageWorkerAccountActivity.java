package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.ManageWorkerAccountItemAdapter;
import whut.qingxie.Item.ManageWorkerAccountItem;

public class ManageWorkerAccountActivity extends AppCompatActivity {
    private List<ManageWorkerAccountItem> manageWorkerAccountItemList =new ArrayList<>();

    private static ManageWorkerAccountItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker_account);
        if(manageWorkerAccountItemList.size()==0)
            init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.manage_worker_account_recyclerView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new ManageWorkerAccountItemAdapter(manageWorkerAccountItemList);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout=(SmartRefreshLayout)findViewById(R.id.manage_worker_account_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                init();
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for(int i=0;i<5;i++) {
                    manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
                }

                //结束加载更多
                if(manageWorkerAccountItemList.size()<10)
                    smartRefreshLayout.finishLoadmore();
                else
                    smartRefreshLayout.finishLoadmoreWithNoMoreData();
                reFresh();
            }
        });
    }

    private static void reFresh(){
        adapter.notifyDataSetChanged();
    }

    private void init(){
        manageWorkerAccountItemList.clear();
        for(int i=0;i<5;i++) {
            manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
        }
        reFresh();
        smartRefreshLayout.setEnableAutoLoadmore(true);
    }
}
