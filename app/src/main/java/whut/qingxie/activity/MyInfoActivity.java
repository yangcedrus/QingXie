package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyInfoItemAdapter;
import whut.qingxie.Item.MyInfoItem;

public class MyInfoActivity extends AppCompatActivity {
    private List<MyInfoItem> myInfoItemArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        //创建ListView
        if(myInfoItemArrayList.size()==0)
            initItems();
        MyInfoItemAdapter adapter=new MyInfoItemAdapter(MyInfoActivity.this,
                R.layout.item_info,myInfoItemArrayList);
        final ListView listView=(ListView)findViewById(R.id.info_listview);
        listView.setAdapter(adapter);


        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_myinfo);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        myInfoItemArrayList.add(new MyInfoItem("类别","你好啊"));
        myInfoItemArrayList.add(new MyInfoItem("性别","你好啊"));
        myInfoItemArrayList.add(new MyInfoItem("学院","你好啊"));
        myInfoItemArrayList.add(new MyInfoItem("年级","你好啊"));
        myInfoItemArrayList.add(new MyInfoItem("专业","你好啊"));
        myInfoItemArrayList.add(new MyInfoItem("班级","你好啊"));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            // TODO: 2018/1/9 活动待删除

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
