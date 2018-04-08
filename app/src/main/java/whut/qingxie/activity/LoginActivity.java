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

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

/**
 * 用户登录页面
 * FIXME:首要！参考一下别人的本地账户管理的实现
 */
public class LoginActivity extends AppCompatActivity {
    //储存登录信息

    private Content content;
    private Integer userID;
    private int Flag;
    private int gender;

    private static String TAG="LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取全局变量
        content = (Content) getApplicationContext();
        userID = content.getUserId();
        Flag=content.getFLAG();
        gender=content.getGENDER();

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //显示返回按钮
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button button_login=(Button)findViewById(R.id.Login_login);
        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //点击登录，登录成功返回信息给上个活动
                // TODO: 2018/1/9 读取数据库信息
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
//                    state=Integer.parseInt(name);
                }
            }
        });
    }

    public void login(String name, String psw) {
        //判断是否全部为数字
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(name);

        HashMap<String, String> paramsMap = new HashMap<>();


        if (m.matches()) {
            //用户id登录
            paramsMap.put("studentId",name);
            paramsMap.put("password",psw);
        } else {
            //用户名登录
            paramsMap.put("name",name);
            paramsMap.put("password",psw);
        }
        OkhttpUtil.accessData("POST", "/user/login", paramsMap, null, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                content.setUserId(0);
                Toast.makeText(LoginActivity.this,"登录失败，请重新登录",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Login()onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Msg msg) {
                Object obj=msg.getData().get("User");

                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
        getMenuInflater().inflate(R.menu.toolbar,menu);
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
                //点击返回键返回信息给上个活动
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
        //点击虚拟返回按钮返回信息给上个活动
        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
