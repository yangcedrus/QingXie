package whut.qingxie.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import whut.qingxie.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent=new Intent();
        intent.putExtra("data_return",1);
        setResult(RESULT_OK,intent);

        final String items[]={"学生","青协工作者","管理员"};
        //dialog参数设置
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setItems(items,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent= new Intent(LoginActivity.this,PSWActivity.class);
                startActivityForResult(intent,2);
                //Toast.makeText(LoginActivity.this, items[which], Toast.LENGTH_SHORT).show();
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

        //帮助按钮点击事件响应
        ImageButton help=(ImageButton)findViewById(R.id.Helps);
        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent= new Intent(LoginActivity.this,FeedBack.class);
                //startActivityForResult(intent,2);
            }});
    }
}
