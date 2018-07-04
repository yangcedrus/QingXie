package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.Item.MyMessageItem;
import whut.qingxie.R;
import whut.qingxie.adapter.MyMessageItemAdapter;

public class FeedbackMessageActivity extends AppCompatActivity {
    private List<MyMessageItem> myMessageItemArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_message);

        //创建ListView
        if(myMessageItemArrayList.size()==0)
            initItems();
        MyMessageItemAdapter adapter=new MyMessageItemAdapter(FeedbackMessageActivity.this,
                R.layout.item_message,myMessageItemArrayList);
        ListView listView=(ListView)findViewById(R.id.feedback_listview);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_feedback);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        myMessageItemArrayList.add(new MyMessageItem("张一","点击首页图片进不去",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张二","没办法登录，点击登录就崩溃",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张三","我随便点一点系统就崩溃了",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张四","我就是发着玩玩，看有没有用",R.drawable.ic_home_black_24dp));
        myMessageItemArrayList.add(new MyMessageItem("张五","哈哈哈哈哈哈哈哈哈",R.drawable.ic_home_black_24dp));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
