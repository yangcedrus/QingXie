package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyExperienceItemAdapter;
import whut.qingxie.Item.ExperienceItem;

public class MyResumeActivity extends AppCompatActivity {
    private List<ExperienceItem> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume);

        if(list.size()==0)
            init();
        MyExperienceItemAdapter adapter=new MyExperienceItemAdapter(MyResumeActivity.this,R.layout.experience_item,list);
        ListView listView=(ListView)findViewById(R.id.experience_resume);
        listView.setAdapter(adapter);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_myresume);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void init(){
        list.add(new ExperienceItem("2012.12.07","参加兮然项目"));
        list.add(new ExperienceItem("2015.08.03","参加青柠檬项目"));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
