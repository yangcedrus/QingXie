package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.Item.MyHoursItem;
import whut.qingxie.R;
import whut.qingxie.adapter.MyHoursItemAdapter;

public class MyHoursActivity extends AppCompatActivity {
    private List<MyHoursItem> myHoursItems=new ArrayList<>();

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

        initInfo();

        if(myHoursItems.size()==0)
            initInfo();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.my_hours_recyclerView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyHoursItemAdapter adapter=new MyHoursItemAdapter(myHoursItems);
        recyclerView.setAdapter(adapter);
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

    private void initInfo(){
        for (int i = 0; i < 5; i++) {
            myHoursItems.add(new MyHoursItem("2018/1/1","东院敬老院","老人",4,true));
        }
    }
}
