package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.Item.MyHoursItem;
import whut.qingxie.R;
import whut.qingxie.adapter.MyHoursItemAdapter;
import whut.qingxie.entity.activity.VolActivityInfo;

public class MyHoursActivity extends AppCompatActivity {
    private List<MyHoursItem> myHoursItems=new ArrayList<>();

    private static MyHoursItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hours);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_myhours);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //显示返回按钮
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if(myHoursItems.size()==0)
            init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.my_hours_recyclerView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new MyHoursItemAdapter(myHoursItems);
        recyclerView.setAdapter(adapter);

        //refresh监听
        smartRefreshLayout=(SmartRefreshLayout)findViewById(R.id.my_hours_refresh);
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
                    myHoursItems.add(new MyHoursItem("2018/1/1","敬老院活动",4,false));
                }
                //结束加载更多
                if(myHoursItems.size()<10)
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

    //显示“反馈”菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //设置“反馈”菜单及返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MyHoursActivity.this, HelpInfoActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void init(){
        int num=myHoursItems.size()==0?5:myHoursItems.size();
        myHoursItems.clear();
        for (int i = 0; i < num; i++) {
            myHoursItems.add(new MyHoursItem("2018/1/1","敬老院活动",4,true));
        }
        smartRefreshLayout.resetNoMoreData();
        reFresh();
    }
}
