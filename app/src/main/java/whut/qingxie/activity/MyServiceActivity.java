package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.bean.ServiceItem;
import whut.qingxie.adapter.ServiceItemAdapter;

public class MyServiceActivity extends AppCompatActivity {

    private List<ServiceItem> serviceItemList=new ArrayList<>();
    static int num=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        //创建ListView
        if(serviceItemList==null)
            initItems();
        ServiceItemAdapter adapter=new ServiceItemAdapter(MyServiceActivity.this,
                R.layout.vol_service,serviceItemList);
        ListView listView=(ListView)findViewById(R.id.service_listview);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_myservice);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        serviceItemList.add(new ServiceItem("XYZ活动","2017-11-11/武汉理工大学",
                num++,"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",1));
        serviceItemList.add(new ServiceItem("ABC活动","2017-11-11/武汉理工大学",
                num++,"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",2));
        serviceItemList.add(new ServiceItem("守卫南湖大草原活动","2017-11-11/武汉理工大学",
                num++,"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",1));
        serviceItemList.add(new ServiceItem("不知道什么活动","2017-11-11/武汉理工大学",
                num++,"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",2));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
