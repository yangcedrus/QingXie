package whut.qingxie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import whut.qingxie.R;
import whut.qingxie.entity.activity.VolActivityInfo;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        VolActivityInfo activityInfo=(VolActivityInfo)getIntent().getParcelableExtra("activity_details");

        //加载布局
        TextView title=(TextView)findViewById(R.id.sign_up_info);
        TextView descriptions=(TextView)findViewById(R.id.sign_up_description);
        TextView people_have=(TextView)findViewById(R.id.textView_people_have);
        TextView people_needed=(TextView)findViewById(R.id.textView_people_needed);

        title.setText(activityInfo.getName());
        descriptions.setText(activityInfo.getDescriptions());
        people_have.setText(Integer.toString(activityInfo.getNeedVolunteers()));
        people_needed.setText(Integer.toString(activityInfo.getNeedVolunteers()));
    }

    //显示“反馈”菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //设置“反馈”菜单按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(SignUpActivity.this, HelpInfoActivity.class);
                startActivity(intent);
        }
        return true;
    }
}
