package whut.qingxie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyMessageItemAdapter;
import whut.qingxie.Item.MyMessageItem;

public class MyMessageActivity extends AppCompatActivity {
    private List<MyMessageItem> myMessageItemArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        //创建ListView
        if(myMessageItemArrayList.size()==0)
            initItems();
        MyMessageItemAdapter adapter=new MyMessageItemAdapter(MyMessageActivity.this,
                R.layout.item_message,myMessageItemArrayList);
        ListView listView=(ListView)findViewById(R.id.message_listview);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_mymessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        myMessageItemArrayList.add(new MyMessageItem("张三","你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张三","你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张三","你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张三","你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张三","你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊",R.drawable.ic_home_black_24dp));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
