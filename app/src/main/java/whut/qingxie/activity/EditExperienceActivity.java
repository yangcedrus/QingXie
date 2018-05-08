package whut.qingxie.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyExperienceItemAdapter;
import whut.qingxie.entity.user.UserExperience;

public class EditExperienceActivity extends AppCompatActivity {
    private ArrayList<UserExperience> userExperiences = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experience);

        //显示返回按钮
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit_experience);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //创建ListView
        Intent intent = getIntent();
        userExperiences = intent.getParcelableArrayListExtra("my_experience");

        data.add("增加");
        data.add("删除");
        data.add("修改");

        MyExperienceItemAdapter adapter = new MyExperienceItemAdapter(EditExperienceActivity.this,
                R.layout.item_experience, userExperiences);
        final ListView listView = (ListView) findViewById(R.id.edit_experience_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final View popupWindow = EditExperienceActivity.this.getLayoutInflater().inflate(R.layout.popupwindow_hours, null);

                ListView listView2 = (ListView) popupWindow.findViewById(R.id.release_work_hours_list);
                listView2.setAdapter(new ArrayAdapter<String>(EditExperienceActivity.this, android.R.layout.simple_list_item_1, data));

                final PopupWindow window = new PopupWindow(popupWindow, dp2px(EditExperienceActivity.this,65), dp2px(EditExperienceActivity.this,145));
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                window.setFocusable(true);
                window.setOutsideTouchable(true);
                window.update();
                window.showAsDropDown(listView, getResources().getDisplayMetrics().widthPixels / 2 - dp2px(EditExperienceActivity.this, 20),
                        -(dp2px(EditExperienceActivity.this, 10) + (userExperiences.size() - position - 1) * dp2px(EditExperienceActivity.this, 25)));

                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = data.get(position);
                        switch (s) {
                            case "增加":
                                Toast.makeText(EditExperienceActivity.this, s + "完成", Toast.LENGTH_SHORT).show();
                                break;
                            case "删除":
                                Toast.makeText(EditExperienceActivity.this, s + "完成", Toast.LENGTH_SHORT).show();
                                break;
                            case "修改":
                                Toast.makeText(EditExperienceActivity.this, s + "完成", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }
        });
    }

    public void deleteExperience(int position) {
    }

    public void addExperience() {

    }

    public void modifyExperience() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
