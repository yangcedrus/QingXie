package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.user.Resume;
import whut.qingxie.entity.user.UserExperience;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

public class EditResumeActivity extends AppCompatActivity {
    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private Resume resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume);

        Intent intent = getIntent();
        resume = intent.getParcelableExtra("my_resume");

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit_resume);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        layout1 = (RelativeLayout) findViewById(R.id.edit_resume_layout1);
        layout2 = (RelativeLayout) findViewById(R.id.edit_resume_layout2);
        layout3 = (RelativeLayout) findViewById(R.id.edit_resume_layout3);
        layout4 = (RelativeLayout) findViewById(R.id.edit_resume_layout4);
        layout1.setOnClickListener(new MyListener());
        layout2.setOnClickListener(new MyListener());
        layout3.setOnClickListener(new MyListener());
        layout4.setOnClickListener(new MyListener());
    }

    /**
     * 返回数据处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        String s = data.getStringExtra("new_string");
        switch (requestCode) {
            case 1:
                resume.setWechat(s);
                break;
            case 2:
                resume.setTelephone(s);
                break;
            case 3:
                resume.setProfile(s);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            uploadResume();
        return super.onOptionsItemSelected(item);
    }

    private class A {
        String email;
        Integer id;
        String qq;
        String teltphone;

        public A(String email, String qq, String teltphone) {
            this.email = email;
            this.qq = qq;
            this.teltphone = teltphone;
            id=Content.getUserId();
        }
    }

    private void uploadResume() {
        A a = new A(resume.getEmail(), resume.getQq(), resume.getTelephone());

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "application/json;charset=UTF-8");
        headerMap.put("user-agent", "android");
        String json = new Gson().toJson(a);

        OkhttpUtil.okHttpPostJson("/user/"+Content.getUserId()+"/info/update", json, headerMap, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(EditResumeActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onResponse(Msg response) {
                Toast.makeText(EditResumeActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra("my_resume",resume);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    /**
     * 监听类
     */
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.edit_resume_layout1:
                    intent = new Intent(EditResumeActivity.this, EditTextActivity.class);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.edit_resume_layout2:
                    intent = new Intent(EditResumeActivity.this, EditTextActivity.class);
                    startActivityForResult(intent, 2);
                    break;
                case R.id.edit_resume_layout3:
                    intent = new Intent(EditResumeActivity.this, EditTextActivity.class);
                    startActivityForResult(intent, 3);
                    break;
                case R.id.edit_resume_layout4:
                    ArrayList<UserExperience> userExperiences=new ArrayList<>();
                    userExperiences.addAll(resume.getExperiences());
                    intent = new Intent(EditResumeActivity.this, EditExperienceActivity.class);
                    intent.putParcelableArrayListExtra("my_experience",userExperiences);
                    startActivity(intent);
                    break;
            }
        }
    }
}
