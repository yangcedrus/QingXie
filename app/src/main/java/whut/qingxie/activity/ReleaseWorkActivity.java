package whut.qingxie.activity;
//发布活动

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.ReleaseWorkItemAdapter;
import whut.qingxie.bean.ReleaseWorkItem;

public class ReleaseWorkActivity extends AppCompatActivity {
    private List<ReleaseWorkItem> releaseWorkItemArrayList=new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_work_activitiy);

        //创建ListView
        if(releaseWorkItemArrayList.size()==0)
            initItems();
        ReleaseWorkItemAdapter adapter=new ReleaseWorkItemAdapter(ReleaseWorkActivity.this,
                R.layout.release_item,releaseWorkItemArrayList);
        ListView listView=(ListView)findViewById(R.id.release_listview);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_release_work);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        releaseWorkItemArrayList.add(new ReleaseWorkItem("活动主题","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("举办单位","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("面向对象","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("活动目的","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("活动详情","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("志愿服务工时","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("需要志愿者人数","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("志愿者要求","你好啊"));
        releaseWorkItemArrayList.add(new ReleaseWorkItem("报名截止时间","你好啊"));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
