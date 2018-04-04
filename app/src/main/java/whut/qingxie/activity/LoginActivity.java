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

import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;

/**
 * FIXME:首要！参考一下别人的本地账户管理的实现
 */
public class LoginActivity extends AppCompatActivity {
    //储存登录信息
    private int state=0;
    
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
             //   }else if(psw.length()==0){
                    //Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    // TODO: 2018/3/9 密码为空 
                }else{
                    // TODO: 2018/3/9 判断是否登陆成功 
                    state=Integer.parseInt(name);
                }

                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("user_state",state);
                startActivity(intent);
                finish();
            }
        });
    }

    public void login(){

    }

    @SuppressLint("HandlerLeak")
    public static Handler eHandler=new Handler(){
        public void handleMessage(Message message){
            super.handleMessage(message);
            try {
                Msg msg=Msg.parseMapFromJson(message.obj,Content.CLAZZ_MAP);

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
                intent.putExtra("user_state",state);
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
        intent.putExtra("user_state",state);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
