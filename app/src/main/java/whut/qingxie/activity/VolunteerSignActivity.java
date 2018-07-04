package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.adapter.VolunteerSignItemAdapter;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.user.UserSign;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

public class VolunteerSignActivity extends AppCompatActivity {
    private List<UserSign> volunteerList = new ArrayList<>();
    private List<A> statusList = new ArrayList<>();
    private VolunteerSignItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_sign);

        Intent intent = getIntent();
        final Integer activityId = intent.getIntExtra("activityID", -1);
        String title = intent.getStringExtra("title");
        String sponsor = intent.getStringExtra("sponsor");

        if (volunteerList.size() == 0)
            init(activityId);
        //创建ListView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vol_sign_recyclerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new VolunteerSignItemAdapter(volunteerList);
        recyclerView.setAdapter(adapter);

        //负责人信息
        TextView tx_title = (TextView) findViewById(R.id.volunteer_sign_title);
        TextView tx_manager = (TextView) findViewById(R.id.volunteer_sign_manager);
        if (title != null)
            tx_title.setText(title);
        if (sponsor != null)
            tx_manager.setText(sponsor);

        Button confirm = (Button) findViewById(R.id.volunteer_sign_confirm);
        Button edit = (Button) findViewById(R.id.volunteer_sign_edit);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json;
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("content-type", "application/json;charset=UTF-8");
                headerMap.put("user-agent", "android");
                formStatus();
                json=new Gson().toJson(statusList);

                OkhttpUtil.okHttpPostJson("/activity/"+activityId+"/arriveConfirm", json, headerMap, new CallBackUtil.CallBackMsg() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        Log.d("", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Msg response) {
                        Toast.makeText(VolunteerSignActivity.this,"成功确认",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json;
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("content-type", "application/json;charset=UTF-8");
                headerMap.put("user-agent", "android");
                formStatus();
                json=new Gson().toJson(statusList);

                OkhttpUtil.okHttpPostJson("/activity/"+activityId+"/modifyConfirm", json, headerMap, new CallBackUtil.CallBackMsg() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        Log.d("", "onFailure: ");
                    }

                    @Override
                    public void onResponse(Msg response) {
                        Toast.makeText(VolunteerSignActivity.this,"成功修改",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vol_sign);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    /**
     * 签到状态内部类
     */
    class A {
        private Integer userId;
        private boolean isArrived;

        public A() {
        }

        public A(Integer userId, boolean isArrived) {
            this.userId = userId;
            this.isArrived = isArrived;
        }
    }

    /**
     * 获取服务器数据，活动下所有志愿者
     *
     * @param activityID 活动ID
     */
    private void init(Integer activityID) {
        OkhttpUtil.okHttpGet("/activity/" + activityID + "/volunteers", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.d("ManageWorkActivity", "onFailure: ");
            }

            @Override
            public void onResponse(Msg response) {
                List<UserSign> list = (List<UserSign>) response.getData().get("UserSignList");
                volunteerList.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * 生成签到状态数组
     */
    private void formStatus() {
        for (int i = 0; i < volunteerList.size(); i++) {
            Integer id = volunteerList.get(i).getId();
            boolean status = volunteerList.get(i).isConfirm();
            A a = new A(id, status);
            statusList.add(a);
        }
    }

}
