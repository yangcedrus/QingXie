package whut.qingxie.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.rmondjone.locktableview.LockTableView;
import com.rmondjone.xrecyclerview.ProgressStyle;
import com.rmondjone.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import whut.qingxie.R;

public class MyHoursActivity extends AppCompatActivity {

    private LinearLayout mContentView;
    final ArrayList<ArrayList<String>> mTableDatas=new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hours);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_myhours);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //显示返回按钮
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initInfo();
        mContentView = (LinearLayout) findViewById(R.id.my_hours_contentView);
        final LockTableView lockTableView=new LockTableView(this,mContentView,mTableDatas);
        lockTableView.setLockFristColumn(false) //是否锁定第一列
                .setLockFristRow(true) //是否锁定第一行
                .setMaxColumnWidth(40) //列最大宽度
                .setMinColumnWidth(20) //列最小宽度
                .setMinRowHeight(10)//行最小高度
                .setMaxRowHeight(10)//行最大高度
                .setTextViewSize(10) //单元格字体大小
                .setFristRowBackGroudColor(R.color.table_head)//表头背景色
                .setTableHeadTextColor(R.color.beijin)//表头字体颜色
                .setTableContentTextColor(R.color.border_color)//单元格字体颜色
                .setNullableString("N/A") //空值替换值
                .setOnLoadingListener(new LockTableView.OnLoadingListener() {//下拉刷新、上拉加载监听
                    // @Override
                    public void onRefresh(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // TODO: 2017/11/11 从数据库拉取数据
                                mTableDatas.clear();
                                initInfo();
                                for (int i = 0; i < 20; i++) {
                                    ArrayList<String> mRowDatas = new ArrayList<String>();
                                    for (int j = 0; j < 5; j++) {
                                        mRowDatas.add("数据" + j);
                                    }
                                    mTableDatas.add(mRowDatas);
                                }
                                lockTableView.setTableDatas(mTableDatas);
                                mXRecyclerView.refreshComplete();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onLoadMore(final XRecyclerView mXRecyclerView, final ArrayList<ArrayList<String>> mTableDatas) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mTableDatas.size() <= 60) {
                                    for (int i = 0; i < 10; i++) {
                                        ArrayList<String> mRowDatas = new ArrayList<String>();
                                        for (int j = 0; j < 5; j++) {
                                            mRowDatas.add("数据" + j);
                                        }
                                        mTableDatas.add(mRowDatas);
                                    }
                                    lockTableView.setTableDatas(mTableDatas);
                                } else {
                                    mXRecyclerView.setNoMore(true);
                                }
                                mXRecyclerView.loadMoreComplete();
                            }
                        }, 1000);
                    }
                }).show();
        lockTableView.getTableScrollView().setPullRefreshEnabled(true);
        lockTableView.getTableScrollView().setLoadingMoreEnabled(true);
        lockTableView.getTableScrollView().setRefreshProgressStyle(ProgressStyle.SquareSpin);
    }

    //显示“反馈”菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //设置“反馈”菜单及返回按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MyHoursActivity.this, HelpInfoActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void initInfo(){
        ArrayList<String> rowdatas=new ArrayList<String>();
        rowdatas.add("服务时间");
        rowdatas.add("服务地点");
        rowdatas.add("服务对象");
        rowdatas.add("工时");
        rowdatas.add("青协认定");
        mTableDatas.add(rowdatas);
    }
}
