package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import me.iwf.photopicker.PhotoPicker;
import whut.qingxie.R;
import whut.qingxie.helper.BottomNavigationViewHelper;
import whut.qingxie.fragment.AdministratorFragment;
import whut.qingxie.fragment.FavouriteFragment;
import whut.qingxie.fragment.HomeFragment;
import whut.qingxie.fragment.MeFragment;
import whut.qingxie.fragment.OperationHistoryFragment;
import whut.qingxie.fragment.WorkerMeFragment;
import whut.qingxie.fragment.WorkerWorkFragment;

public class MainActivity extends AppCompatActivity {

    private static final int NO_LOGIN=0;     //未登录
    private static final int STUDENT=1;     //学生
    private static final int WORKER=2;      //工作人员
    private static final int ADMIN=3;       //管理员
    private static final int LOG_IN=101;
    private static final int LOG_OUT=102;

    public static int state=NO_LOGIN;

    private HomeFragment mHomeFragment;
    private MeFragment mMeFragment;
    private FavouriteFragment mFavouriteFragment;
    private WorkerMeFragment mWorkerMeFragment;
    private WorkerWorkFragment mWorkerWorkFragment;
    private OperationHistoryFragment mOperationHistoryFragment;
    private AdministratorFragment mAdministratorFragment;
    private static BottomNavigationView bottomNavigationView;

    /**
     * 返回登录信息
     * @param requestCode 请求码
     * @param resultCode 返回码
     * @param data 传递的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOG_IN:
                state=data.getIntExtra("login_state_return",0);
                if(resultCode==RESULT_OK){
                    switch (state){
                        case STUDENT:
                            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_favorite_border_black_24dp);
                            bottomNavigationView.getMenu().getItem(2).setTitle("收藏");
                            break;
                        case WORKER:
                            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_group_black_24dp);
                            bottomNavigationView.getMenu().getItem(2).setTitle("工作");
                            break;
                        case ADMIN:
                            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_group_black_24dp);
                            bottomNavigationView.getMenu().getItem(2).setTitle("工作");
                            bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_detail_black_24dp);
                            bottomNavigationView.getMenu().getItem(1).setTitle("操作历史");
                            break;
                        default:break;
                    }
                }
                break;
            case LOG_OUT:
                int flag=data.getIntExtra("log_out",0);
                if(flag==1) {
                    state = 0;
                }
                break;
            case PhotoPicker.REQUEST_CODE:
                FragmentManager manager=getSupportFragmentManager();
                Fragment fragment=manager.findFragmentByTag("me_fragment");
                fragment.onActivityResult(requestCode,resultCode,data);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //设置默认的Fragment
        setDefaultFragment();

        //底部导航栏切换操作
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        //得到Fragment信息
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();

                        //在切换之前判断是否已经登录
                        if(state==NO_LOGIN&&item.getItemId()!=R.id.navigation_home){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent, LOG_IN);
                        }else{
                            switch(item.getItemId()){
                                case R.id.navigation_home:
                                    //点击第一个Item
                                    if (mHomeFragment == null) {
                                        mHomeFragment = new HomeFragment();
                                    }
                                    transaction.replace(R.id.content, mHomeFragment,"home_fragment");
                                    break;
                                case R.id.navigation_me:
                                    //点击第二个Item
                                    //判断登陆状态
                                    switch(state) {
                                        case STUDENT:
                                            if (mMeFragment == null) {
                                                mMeFragment = new MeFragment();
                                            }
                                            transaction.replace(R.id.content, mMeFragment,"me_fragment");
                                            break;
                                        case WORKER:
                                            if (mWorkerMeFragment == null) {
                                                mWorkerMeFragment = new WorkerMeFragment();
                                            }
                                            transaction.replace(R.id.content, mWorkerMeFragment,"worker_me_fragment");
                                            break;
                                        case ADMIN:
                                            if (mOperationHistoryFragment == null) {
                                                mOperationHistoryFragment = new OperationHistoryFragment();
                                            }
                                            transaction.replace(R.id.content, mOperationHistoryFragment,"admin_history_fragment");
                                            break;
                                        case NO_LOGIN:
                                            break;
                                    }
                                    break;
                                case R.id.navigation_favourite:
                                    //点击第三个Item
                                    //判断登陆状态
                                    switch(state) {
                                        case STUDENT:
                                            if (mFavouriteFragment == null) {
                                                mFavouriteFragment = new FavouriteFragment();
                                            }
                                            transaction.replace(R.id.content, mFavouriteFragment,"favourite_fragment");
                                            break;
                                        case WORKER:
                                            if (mWorkerWorkFragment == null) {
                                                mWorkerWorkFragment = new WorkerWorkFragment();
                                            }
                                            transaction.replace(R.id.content, mWorkerWorkFragment,"worker_work_fragment");
                                            break;
                                        case ADMIN:
                                            if (mAdministratorFragment == null) {
                                                mAdministratorFragment = new AdministratorFragment();
                                            }
                                            transaction.replace(R.id.content, mAdministratorFragment,"admin_work_fragment");
                                            break;
                                        case NO_LOGIN:
                                            break;
                                    }
                                    break;
                            }
                        }
                        transaction.commit();
                        return true;
                    }
                });
    }

    /**
     * 设置初始Fragment
     */
    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        mHomeFragment = new HomeFragment();
        transaction.replace(R.id.content,mHomeFragment);
        transaction.commit();
    }

    /**
     * 显示“系统设置”菜单
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * 设置系统设置菜单响应
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SystemSettingsActivity.class);
                startActivityForResult(intent,LOG_OUT);
        }
        return true;
    }

}
