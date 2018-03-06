package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import whut.qingxie.R;

public class LoginActivity extends AppCompatActivity {
    //储存登录信息
    private int state=0;

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
                String s=textView.getText().toString();
                if(!s.equals(""))
                    state=Integer.parseInt(s);

                Intent intent=new Intent();
                intent.putExtra("login_state_return",state);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

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
                intent=new Intent();
                intent.putExtra("login_state_return",state);
                setResult(RESULT_OK,intent);
                finish();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //点击虚拟返回按钮返回信息给上个活动
        Intent intent=new Intent();
        intent.putExtra("login_state_return",state);
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }
}
