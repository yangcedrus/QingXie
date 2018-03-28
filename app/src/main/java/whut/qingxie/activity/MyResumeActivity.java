package whut.qingxie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import whut.qingxie.R;
import whut.qingxie.adapter.MyExperienceItemAdapter;
import whut.qingxie.Item.ExperienceItem;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.user.Resume;
import whut.qingxie.entity.user.UserExperience;
import whut.qingxie.entity.user.User;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

public class MyResumeActivity extends AppCompatActivity {
    private List<ExperienceItem> list = new ArrayList<>();
    private TextView tx_age;
    private TextView tx_profile;
    private TextView tx_politics;
    private TextView tx_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume);
        tx_age = (TextView) findViewById(R.id.age_resume);
        tx_politics = (TextView) findViewById(R.id.pilitics_resume);
        tx_profile = (TextView) findViewById(R.id.profile_resume);
        tx_class = (TextView) findViewById(R.id.class_resume);

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_myresume);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        User myInfo = (User) intent.getParcelableExtra("user_info");

        if (list.size() == 0)
            init();
        MyExperienceItemAdapter adapter = new MyExperienceItemAdapter(MyResumeActivity.this, R.layout.item_experience, list);
        ListView listView = (ListView) findViewById(R.id.experience_resume);
        listView.setAdapter(adapter);
        // TODO: 2018/3/9 无信息可写 
    }

    @SuppressLint("HandlerLeak")
    private Handler eHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                Msg msg = Msg.parseMapFromJson(message.obj, Content.CLAZZ_MAP);
                Resume resume = (Resume) msg.getData().get("Resume");
                List<UserExperience> experiences = resume.getExperiences();
                for (UserExperience exp : experiences) {
                    list.add(new ExperienceItem(exp.getEnd().toString(), exp.getActivityName()));
                }
            } catch (ClassNotFoundException e) {
                Log.e("MyResumeActivity", "handleMessage: " + e.getMessage());
            }
        }
    };

    private void getExperience() {
        OkhttpUtil.accessData("GET", "/user/3/resume", null, null, new CallBackUtil<Msg>() {
            @Override
            public Msg onParseResponse(Call call, Response response) {
                try {
                    return Msg.parseMapFromJson(response.body().string(), Content.CLAZZ_MAP);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    Log.e("MyResumeActivity", "handleMessage: " + e.getMessage());
                }
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("MyResumeActivity", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg msg) {
                if (msg != null) {
                    Resume resume = (Resume) msg.getData().get("Resume");
                    if(resume==null){
                        // TODO: 2018/3/24 未返回数据的处理 
                        return;
                    }
                    List<UserExperience> experiences = resume.getExperiences();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (UserExperience exp : experiences) {
                        list.add(new ExperienceItem(format.format(exp.getEnd()), exp.getActivityName()));
                    }
                    MyExperienceItemAdapter adapter = new MyExperienceItemAdapter(MyResumeActivity.this, R.layout.item_experience, list);
                    ListView listView = (ListView) findViewById(R.id.experience_resume);
                    listView.setAdapter(adapter);
                    tx_age.setText(resume.getAge()+"岁");
                    tx_class.setText(resume.getClassName());
                    tx_politics.setText(resume.getPoliticalStatus());
                    tx_profile.setText(resume.getProfile());
                }

            }
        });
    }

    public void init() {
        getExperience();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        list.add(new ExperienceItem("2012.12.07","参加兮然项目"));
//        list.add(new ExperienceItem("2015.08.03","参加青柠檬项目"));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
