package whut.qingxie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.Item.ExperienceItem;
import whut.qingxie.R;
import whut.qingxie.adapter.MyExperienceItemAdapter;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.user.Resume;
import whut.qingxie.entity.user.User;
import whut.qingxie.entity.user.UserExperience;
import whut.qingxie.Item.ExperienceItem;
import whut.qingxie.entity.user.UserExperience;
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
    }

    private void getExperience() {
        //FIXME:API更改
        Integer id = 3;
        OkhttpUtil.accessData("GET", "/user/" + id + "/resume", null, null, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:网络访问错误，超时等处理
                Log.e("MyResumeActivity", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg msg) {
                if (msg != null) {
                    Resume resume = (Resume) msg.getData().get("Resume");
                    if(resume==null){
                        // TODO: 2018/3/24 未返回数据的处理,直接从本地获取或者返回错误信息
                        return;
                    }
                    setDefaultViews(resume);
                }

            }
        });
    }

    public void init() {
        //查看本地数据
        // TODO: 2018/4/4 根据session判断是否从本地获取
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        Integer id = preferences.getInt("user_id", -1);
        if (id != -1) {
            Resume resume = new Resume();
            resume.setUserId(preferences.getInt("user_id", -1));
            resume.setStudentId(preferences.getString("user_student_id", null));
            resume.setAge(preferences.getInt("user_age",0));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date=null;
            try {
                date=format.parse(preferences.getString("user_birthday",null));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            resume.setBirthDate(date);

            resume.setPoliticalStatus(preferences.getString("user_political_status",null));
            resume.setEmail(preferences.getString("user_email",null));
            resume.setName(preferences.getString("user_name",null));
            resume.setProfile(preferences.getString("user_profile",null));
            resume.setQq(preferences.getString("user_qq",null));
            resume.setTelephone(preferences.getString("user_telephone",null));
            resume.setWechat(preferences.getString("user_wechat",null));
            resume.setGender(preferences.getString("user_gender","M"));

            String json=preferences.getString("user_experiences",null);
            if (json!=null){
                Gson gson=new Gson();
                List<UserExperience> experience=gson.fromJson(json,new TypeToken<List<UserExperience>>(){}.getType());
                resume.setExperiences(experience);
            }

            setDefaultViews(resume);
        } else {
            getExperience();
        }
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void setDefaultViews(Resume resume) {
        List<UserExperience> experiences = resume.getExperiences();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (UserExperience exp : experiences) {
            list.add(new ExperienceItem(format.format(exp.getEnd()), exp.getActivityName()));
        }
        MyExperienceItemAdapter adapter = new MyExperienceItemAdapter(MyResumeActivity.this, R.layout.item_experience, list);
        ListView listView = (ListView) findViewById(R.id.experience_resume);
        listView.setAdapter(adapter);
        tx_age.setText(resume.getAge() + "岁");
        tx_class.setText(resume.getClassName());
        tx_politics.setText(resume.getPoliticalStatus());
        tx_profile.setText(resume.getProfile());

        //保存到本地
        SharedPreferences.Editor editor=getSharedPreferences("user_info",MODE_PRIVATE).edit();
        editor.putInt("user_id",resume.getUserId());
        editor.putString("user_student_id",resume.getStudentId());
        editor.putInt("user_age",resume.getAge());
        editor.putString("user_political_status",resume.getPoliticalStatus());
        editor.putString("user_email",resume.getEmail());
        editor.putString("user_name",resume.getName());
        editor.putString("user_profile",resume.getProfile());
        editor.putString("user_qq",resume.getQq());
        editor.putString("user_telephone",resume.getTelephone());
        editor.putString("user_wechat",resume.getWechat());
        editor.putString("user_gender",resume.getGender());

        String date=format.format(resume.getBirthDate());
        editor.putString("user_birthday",date);

        Gson gson=new Gson();
        String json=gson.toJson(resume.getExperiences());
        editor.putString("user_experiences",json);

        editor.apply();
    }
}
