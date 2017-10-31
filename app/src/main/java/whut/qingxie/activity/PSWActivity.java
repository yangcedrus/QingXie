package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import whut.qingxie.R;

public class PSWActivity extends AppCompatActivity {
    private int state=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psw);

        //设置文字
        Intent intent1=getIntent();
        String info=intent1.getStringExtra("extra_info")+"登录";
        TextView textView=(TextView)findViewById(R.id.psw_textView);
        textView.setText(info);

        //登录按钮响应
        ImageButton button=(ImageButton)findViewById(R.id.Login_OK);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                state=1;
                Intent intent=new Intent();
                intent.putExtra("data_return",state);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        //取消按钮响应
        ImageButton button2=(ImageButton)findViewById(R.id.Login_Cancel);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                state=0;
                Intent intent=new Intent();
                intent.putExtra("data_return",state);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
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
