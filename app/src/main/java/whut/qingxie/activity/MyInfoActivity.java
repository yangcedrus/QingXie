package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyInfoItemAdapter;
import whut.qingxie.bean.MyInfoItem;

public class MyInfoActivity extends AppCompatActivity {
    private List<MyInfoItem> myInfoItemArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        //创建ListView
        if(myInfoItemArrayList.size()==0)
            initItems();
        MyInfoItemAdapter adapter=new MyInfoItemAdapter(MyInfoActivity.this,
                R.layout.info_item,myInfoItemArrayList);
        final ListView listView=(ListView)findViewById(R.id.info_listview);
        listView.setAdapter(adapter);

        //设置监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        setPop(view);
                        break;
                    case 1:
                        setPop(view);
                        break;
                    case 2:
                        setPop(view);
                        break;
                    case 3:
                        setPop(view);
                        break;
                    case 4:
                        setPop(view);
                        break;
                    case 5:
                        setPop(view);
                        break;
                    default:
                        break;
                }
            }
        });

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_myinfo);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems(){
        myInfoItemArrayList.add(new MyInfoItem("类别","你好啊",R.drawable.ic_dashboard_black_24dp));
        myInfoItemArrayList.add(new MyInfoItem("性别","你好啊",R.drawable.ic_dashboard_black_24dp));
        myInfoItemArrayList.add(new MyInfoItem("学院","你好啊",R.drawable.ic_dashboard_black_24dp));
        myInfoItemArrayList.add(new MyInfoItem("年级","你好啊",R.drawable.ic_dashboard_black_24dp));
        myInfoItemArrayList.add(new MyInfoItem("专业","你好啊",R.drawable.ic_dashboard_black_24dp));
        myInfoItemArrayList.add(new MyInfoItem("班级","你好啊",R.drawable.ic_dashboard_black_24dp));
    }

    //返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            // TODO: 2018/1/9 保存修改的信息

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //弹出选项
    private void setPop(View view){
        PopupMenu mPopup;
        Menu menu;

        mPopup=new PopupMenu(this,view);
        menu = mPopup.getMenu();

        menu.add(Menu.NONE, Menu.FIRST + 0, 1, "哈哈2");
        menu.add(Menu.NONE, Menu.FIRST + 0, 1, "哈哈3");

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu, menu);

        // TODO: 2018/1/9 点击响应事件 

        mPopup.show();
    }
}
