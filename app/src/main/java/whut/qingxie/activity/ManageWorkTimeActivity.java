package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.ManageWorkTimeItemAdapter;
import whut.qingxie.Item.ManageWorkTimeItem;

public class ManageWorkTimeActivity extends AppCompatActivity {
    private List<ManageWorkTimeItem> manageWorkTimeItemList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_work_time);

        //初始化数据
        initInfo();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.manage_work_time_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ManageWorkTimeItemAdapter manageWorkTimeItemAdapter=new ManageWorkTimeItemAdapter(manageWorkTimeItemList);
        recyclerView.setAdapter(manageWorkTimeItemAdapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_manage_work_time);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initInfo(){
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
        manageWorkTimeItemList.add(new ManageWorkTimeItem(R.mipmap.ic_launcher_round,"张三","任务标题","任务摘要/任务时间/认证工时数"));
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
