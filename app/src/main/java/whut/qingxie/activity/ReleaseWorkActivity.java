package whut.qingxie.activity;
//发布活动

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.iwf.photopicker.PhotoPicker;
import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

/**
 * WorkerWorkFragment第五个item
 * 发布志愿活动
 * todo 界面待修改
 */
public class ReleaseWorkActivity extends AppCompatActivity {
    private EditText title, sponsor, participant, place, general, people, people_requirement, reg_end_time;
    private ImageView home_image,set_hours, edit_description;
    private TextView show_hours;

    private VolActivityInfo activityInfo;
    private List<Double> data = new ArrayList<>();

    private final static int REQUEST_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_work_activitiy);

        initData();

        //控件初始化
        activityInfo = new VolActivityInfo();
        home_image=(ImageView)findViewById(R.id.release_edit_homeImage);
        home_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第三方加载
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(false)
                        .setPreviewEnabled(false)
                        .start(ReleaseWorkActivity.this);
            }
        });
        title = (EditText) findViewById(R.id.release_edit_title);
        sponsor = (EditText) findViewById(R.id.release_edit_sponsor);
        participant = (EditText) findViewById(R.id.release_edit_participant);
        place=(EditText)findViewById(R.id.release_edit_place);
        general = (EditText) findViewById(R.id.release_edit_general);
        people = (EditText) findViewById(R.id.release_edit_people);
        people_requirement = (EditText) findViewById(R.id.release_edit_people_requirement);
        reg_end_time = (EditText) findViewById(R.id.release_edit_regEnd_time);
        set_hours = (ImageView) findViewById(R.id.release_edit_hours);
        set_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View popupWindow = ReleaseWorkActivity.this.getLayoutInflater().inflate(R.layout.popupwindow_hours, null);

                ListView listView = (ListView) popupWindow.findViewById(R.id.release_work_hours_list);
                listView.setAdapter(new ArrayAdapter<Double>(ReleaseWorkActivity.this, android.R.layout.simple_list_item_1, data));

                final PopupWindow window = new PopupWindow(popupWindow, 170, 400);
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                window.setFocusable(true);
                window.setOutsideTouchable(true);
                window.update();
                window.showAsDropDown(set_hours, -100, 20);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = data.get(position).toString();
                        show_hours.setText(s);
                        window.dismiss();
                    }
                });
            }
        });
        show_hours = (TextView) findViewById(R.id.release_show_hours);
        edit_description = (ImageView) findViewById(R.id.release_edit_description);
        edit_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReleaseWorkActivity.this, RichTextEditorActivity.class);
                intent.putExtra("release_work_title", title.getText().toString());
                intent.putExtra("title","活动详情");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        Button sendActivity = (Button) findViewById(R.id.release_work_button);
        sendActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkActivityInfo()) {
                    releaseWork();
                    finish();
                }
            }
        });

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_release_work);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * FIXME 检查发布的活动是否符合
     * @return 是否符合规范, true为符合
     */
    private boolean checkActivityInfo() {
        //检查标题字数
        if (title.getText().toString().equals(""))
            return false;
        //检查举办方字数
        if (sponsor.getText().toString().equals(""))
            return false;
        //检查参与对象字数
        if (participant.getText().toString().equals(""))
            return false;
        //检查地点字数
        if(place.getText().toString().equals(""))
            return false;
        //检查概况字数
        if (general.getText().toString().equals(""))
            return false;
        //检查数字格式
        if (people.getText().toString().equals(""))
            return false;
        //检查要求字数限制
        if (people_requirement.getText().toString().equals(""))
            return false;
        //检查日期格式
        if (reg_end_time.getText().toString().equals(""))
            return false;
        return true;
    }

    /**
     * TODO 发布活动，接口未完成
     */
    private void releaseWork() {
        formActivity();

        String json;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "application/json;charset=UTF-8");
        headerMap.put("user-agent", "android");

        json = new Gson().toJson(activityInfo);

        OkhttpUtil.okHttpPostJson("/activity/releaseActivity", json, headerMap, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(Msg response) {
                if(response.getStatus().equals("success"))
                    Toast.makeText(ReleaseWorkActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void formActivity() {
        activityInfo.setName(title.getText().toString());
        activityInfo.setManagerId(Content.getUserId());
        activityInfo.setCreateTime(new Date());
        activityInfo.setGeneral(general.getText().toString());
        //FIXME 每次工时
        activityInfo.setHourPerTime(1.0);
        activityInfo.setHours(Double.parseDouble(show_hours.getText().toString()));
        activityInfo.setNeedVolunteers(Integer.parseInt(people.getText().toString()));
        activityInfo.setStatus(1);
        activityInfo.setPlace(place.getText().toString());
    }

    private void initData() {
        data.add(0.5);
        data.add(1.0);
        data.add(1.5);
        data.add(2.0);
        data.add(2.5);
        data.add(3.0);
        data.add(3.5);
        data.add(4.0);
        data.add(4.5);
        data.add(5.0);
        data.add(5.5);
        data.add(6.0);
        data.add(6.5);
        data.add(7.0);
        data.add(7.5);
        data.add(8.0);
        data.add(8.5);
        data.add(9.0);
        data.add(9.5);
        data.add(10.0);
    }

    /**
     * 返回活动详情
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE:
                    if (data != null) {
                        String s = data.getDataString();
                        if (s != null)
                            activityInfo.setDescriptions(s);
                    }
                    break;
                case PhotoPicker.REQUEST_CODE:
                    if(data!=null){
                        ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                        for(String s:photos){
                            Log.d("photoPath:", s);
                            upload_image(s);
                        }
                    }
                    break;
            }
        }
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            showDialog();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }

    /**
     * 设置退出时对话框
     */
    private void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_exit_black_24dp)//设置标题的图片
                .setTitle("退出")//设置对话框的标题
                .setMessage("确定要退出吗？")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 上传图片
     * @param imagePath
     */
    private void upload_image(String imagePath) {
        File file = new File(imagePath);

        if (!file.exists())
            return;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "multipart/form-data; boundary=" + UUID.randomUUID().toString());
        headerMap.put("connection", "keep-alive");
        headerMap.put("user-agent", "android");

        //FIXME 原本Okhttputil无法完成
        OkHttpUtils.post()//
                .addFile("pic", file.getName(), file)
                .url(Content.getServerHost() + "/activity/" + Content.getUserId() + "/pic/add")
                .headers(headerMap)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ReleaseWorkActivity.this, "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Msg msg = gson.fromJson(response, Msg.class);
                        if (msg.getStatus().equals("error")) {
                            Toast.makeText(ReleaseWorkActivity.this, "图片上传失败", Toast.LENGTH_LONG).show();
                        } else {
                            String image = (String) msg.getData().get("picAccessPath");
                            activityInfo.setHomePagePath(image);
                        }
                    }
                });
    }
}
