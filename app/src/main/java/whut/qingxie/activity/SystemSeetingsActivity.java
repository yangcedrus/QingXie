package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyItem;
import whut.qingxie.adapter.MyItemAdapter;

public class SystemSeetingsActivity extends AppCompatActivity {

    private List<MyItem> myItemList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemseetings);

        //创建ListView
        initItems();
        MyItemAdapter adapter=new MyItemAdapter(SystemSeetingsActivity.this,
                R.layout.my_item,myItemList);
        ListView listView=(ListView)findViewById(R.id.system_list_view);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_system);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        myItemList.add(new MyItem("新消息通知"));
        myItemList.add(new MyItem("通用设置"));
        myItemList.add(new MyItem("密码设置"));
        myItemList.add(new MyItem("检测更新"));
        myItemList.add(new MyItem("版本说明"));
        myItemList.add(new MyItem("反馈"));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
