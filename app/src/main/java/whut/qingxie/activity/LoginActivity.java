package whut.qingxie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.user.User;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

/**
 * 用户登录页面
 */
public class LoginActivity extends AppCompatActivity {
    private int state;

    private static String TAG="LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //显示返回按钮
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button button_login=(Button)findViewById(R.id.Login_login);
        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //点击登录，登录成功返回信息给上个活动
                TextView textView=(TextView)findViewById(R.id.text_name);
                TextView textView1=(TextView)findViewById(R.id.text_psw); 
                String name=textView.getText().toString();
                String psw=textView1.getText().toString();
                
                if(name.length()==0){
                    Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                    return;
                }else if(psw.length()==0){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    login(name,psw);
                }
            }
        });
    }

    public class A {
        String studentId;
        String password;

        public A(String studentId, String password) {
            this.studentId = studentId;
            this.password = password;
        }
    }

    public class B {
        String name;
        String password;

        public B(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }

    public void login(String name, String psw) {
        //判断是否全部为数字
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(name);

        String json;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "application/json;charset=UTF-8");
        headerMap.put("user-agent", "android");

        if (m.matches()) {
            //用户id登录
            json = new Gson().toJson(new A(name, psw));
        } else {
            //用户名登录
            json = new Gson().toJson(new B(name, psw));
        }
        OkhttpUtil.okHttpPostJson("/user/login", json, headerMap, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Content.setUserId(-1);
                Toast.makeText(LoginActivity.this,"登录失败，请重新登录",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Login()onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Msg msg) {
                if(msg.getStatus().equals("success")){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                    praseMSG(msg);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"登录失败，账号密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 将数据存到本地
     * @param msg
     */
    public void praseMSG(Msg msg){
        try{
            User user=(User)msg.getData().get("User");
            String iconAccessPath=(String)msg.getData().get("iconAccessPath");

            int flag=-1;
            switch (user.getFlag()){
                case "S":flag=0;break;
                case "Q":flag=1;break;
                case "A":flag=2;break;
                default:break;
            }

            Content.setIconAccessPath(iconAccessPath);
            Content.setUserId(user.getId());
            Content.setStudentId(user.getStudentId());
            Content.setNAME(user.getName());
            Content.setFLAG(flag);
            Content.setGENDER(user.getGender());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("HandlerLeak")
    public static Handler eHandler=new Handler(){
        public void handleMessage(Message message){
            super.handleMessage(message);
            try {
                Msg msg = Msg.parseMapFromJson(message.obj, Content.getClazzMap());

            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleMessage: "+e.getMessage());
            }
        }
    };

    //显示“帮助”菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.black_help_toolbar,menu);
        return true;
    }

    //设置“帮助”菜单按钮响应以及返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.settings:
                intent = new Intent(LoginActivity.this, HelpInfoActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
            {
                //点击返回键
                intent =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //点击虚拟返回按钮
        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
