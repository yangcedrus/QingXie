package whut.qingxie.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sendtion.xrichtext.RichTextView;

import java.text.SimpleDateFormat;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.helper.ImageUtils;
import whut.qingxie.helper.ScreenUtils;
import whut.qingxie.helper.StringUtils;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

/**
 * 在任何页面点击活动，进入该页面
 * 报名页面
 */
public class SignUpActivity extends AppCompatActivity {
    private static RichTextView et_new_content;

    private int thisActivityId;

    private Button button_sign_up = null;

    @SuppressLint("HandlerLeak")
    public static Handler eHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                Msg msg = Msg.parseMapFromJson(message.obj, Content.getClazzMap());
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleMessage: " + e.getMessage());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //显示返回按钮
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        thisActivityId = getIntent().getIntExtra("activity_details", 0);
        setActivityInfo(thisActivityId);

        button_sign_up = (Button) findViewById(R.id.sign_up_confirm);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Content.getUserId() == -1) {
                    Toast.makeText(SignUpActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }else{
                    showDialog();
                }
            }
        });
    }

    /**
     * 加载活动信息
     *
     * @param ID
     */
    private void setActivityInfo(int ID) {
        OkhttpUtil.accessData("GET", "/activity/" + ID + "/details", null, null, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(SignUpActivity.this, "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                Log.e("SignUpActivity", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg response) {
                final VolActivityInfo activityInfo = (VolActivityInfo) response.getData().get("Activity");
                if (activityInfo == null) {
                    return;
                }

                if (activityInfo.getStatus() != 1) {
                    if (button_sign_up == null) {
                        button_sign_up = (Button) findViewById(R.id.sign_up_confirm);
                    }
                    button_sign_up.setVisibility(View.INVISIBLE);
                }

                //加载布局
                TextView title = (TextView) findViewById(R.id.sign_up_info);
                et_new_content = (RichTextView) findViewById(R.id.sign_up_description);
                TextView sponsor =(TextView)findViewById(R.id.textView_sponsor);
                TextView count=(TextView)findViewById(R.id.textView_activity_count);
                TextView endTime=(TextView)findViewById(R.id.textView_sign_up_end_time);
                TextView people_needed = (TextView) findViewById(R.id.textView_people_needed);
                TextView place=(TextView)findViewById(R.id.textView_place);
                TextView total_hours=(TextView)findViewById(R.id.textView_total_hours);
                title.setText(activityInfo.getName());
                et_new_content.post(new Runnable() {
                    @Override
                    public void run() {
                        showTextData(activityInfo.getDescriptions());
                    }
                });
                sponsor.setText(activityInfo.getSponsor());
                int counts=(int) (activityInfo.getHours()/activityInfo.getHourPerTime());
                count.setText(counts+"");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                endTime.setText(sdf.format(activityInfo.getRegEndTime()));
                people_needed.setText(Integer.toString(activityInfo.getNeedVolunteers()));
                place.setText(activityInfo.getPlace());
                total_hours.setText((activityInfo.getHours()).toString());
            }
        });

        OkhttpUtil.okHttpGet("/activity/"+thisActivityId+"/getApplyNumber", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(SignUpActivity.this, "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                Log.e("SignUpActivity", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg response) {
                TextView people_have = (TextView) findViewById(R.id.textView_people_have);
                people_have.setText( response.getData().get("applyNumber")+"");
            }
        });
    }

    /**
     * 显示详情内容
     *
     * @param content
     */
    private void showTextData(String content) {
        et_new_content.clearAllLayout();
        if (content == null) {
            et_new_content.addTextViewAtIndex(et_new_content.getLastIndex(), "详情为空，如有问题请反馈");
            return;
        }
        List<String> textList = StringUtils.cutStringByImgTag(content);
        for (int i = 0; i < textList.size(); i++) {
            String text = textList.get(i);
            if (text.contains("<img")) {
                final String imagePath = StringUtils.getImgSrc(text);
                int width = ScreenUtils.getScreenWidth(this);
                int height = ScreenUtils.getScreenHeight(this);
                et_new_content.measure(0, 0);
                if (text.contains("http")) {
                    et_new_content.addImageViewAtIndex(et_new_content.getLastIndex(), imagePath);
                }
            } else {
                et_new_content.addTextViewAtIndex(et_new_content.getLastIndex(), text);
            }
        }
    }

    /**
     * 活动报名
     *
     * @param activityID
     */
    private void sign_up(Integer activityID) {
        OkhttpUtil.accessData("POST", "/activity/" + activityID + "/" + Content.getUserId() + "/join", null, null, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(SignUpActivity.this, "连接超时，报名失败，请检查网络连接", Toast.LENGTH_LONG).show();
                Log.e("SignUpActivity", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg response) {
                if (response.getStatus().equals("success")) {
                    Toast.makeText(SignUpActivity.this, "报名成功", Toast.LENGTH_LONG).show();
                    Log.e("SignUpActivity", Content.getUserId() + "报名成功");
                } else {
                    if (response.getMessage().equals("重复报名")) {
                        Toast.makeText(SignUpActivity.this, "重复报名", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    /**
     * 显示特殊按钮
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.white_help_toolbar, menu);
        return true;
    }

    /**
     * 设置特殊按钮响应
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(SignUpActivity.this, HelpInfoActivity.class);
                startActivity(intent);
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 点击返回按钮
     */
    @Override
    public void onBackPressed() {
        //点击虚拟返回按钮
        finish();
        super.onBackPressed();
    }

    /**
     * 设置报名对话框
     */
    private void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_home_black_24dp)//设置标题的图片
                .setTitle("活动报名")//设置对话框的标题
                .setMessage("是否要报名")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("报名", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Content.getUserId() != -1)
                            sign_up(thisActivityId);
                    }
                }).create();
        dialog.show();
    }

}
