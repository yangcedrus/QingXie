package whut.qingxie.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import whut.qingxie.R;

public class LoginActivity extends AppCompatActivity {
    //储存登录信息
    private int state=0;

    //返回登录者身份信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 2:
                state=data.getIntExtra("data_return",0);
        }
        if(state==1) {
            Intent intent=new Intent();
            intent.putExtra("data_return",state);
            setResult(RESULT_OK,intent);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final String items[]={"学生","青协工作者","管理员"};
        //dialog参数设置
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setItems(items,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent= new Intent(LoginActivity.this,PSWActivity.class);
                intent.putExtra("extra_info",items[which]);
                startActivityForResult(intent,2);
            }
        });

        //登录按钮点击事件响应
        Button loginButton=(Button)findViewById(R.id.Login_Button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                builder.create().show();
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
                intent = new Intent(LoginActivity.this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
            {
                //返回信息给上个活动
                intent=new Intent();
                intent.putExtra("data_return",state);
                setResult(RESULT_OK,intent);
                finish();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //返回信息给上个活动
        Intent intent=new Intent();
        intent.putExtra("data_return",state);
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }
}
