package whut.qingxie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import whut.qingxie.R;
import whut.qingxie.adapter.BottomNavigationViewHelper;
import whut.qingxie.fragment.AdministratorFragment;
import whut.qingxie.fragment.FavouriteFragment;
import whut.qingxie.fragment.HomeFragment;
import whut.qingxie.fragment.MeFragment;
import whut.qingxie.fragment.OperationHistoryFragment;
import whut.qingxie.fragment.WorkerMeFragment;
import whut.qingxie.fragment.WorkerWorkFragment;

public class MainActivity extends AppCompatActivity {

    private static final int NOLOGIN=0;     //未登录
    private static final int STUDENT=1;     //学生
    private static final int WORKER=2;      //工作人员
    private static final int ADMIN=3;       //管理员

    private int state=NOLOGIN;

    //返回登录者身份信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 1:
                state=data.getIntExtra("login_state_return",0);
        }
        //无法重新填充布局
        //bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        //if(state==2||state==3){
        //    bottomNavigationView.inflateMenu(R.menu.navigation_worker);
        //}
        super.onActivityResult(requestCode, resultCode, data);
    }

    private HomeFragment mHomeFragment;
    private MeFragment mMeFragment;
    private FavouriteFragment mFavouriteFragment;
    private WorkerMeFragment mWorkerMeFragment;
    private WorkerWorkFragment mWorkerWorkFragment;
    private OperationHistoryFragment mOperationHistoryFragment;
    private AdministratorFragment mAdministratorFragment;

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
        BottomNavigationView bottomNavigationView;
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
                        if(state==NOLOGIN&&item.getItemId()!=R.id.navigation_home){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent, 1);
                        }else{
                            switch(item.getItemId()){
                                case R.id.navigation_home:
                                    //点击第一个Item
                                    if (mHomeFragment == null) {
                                        mHomeFragment = new HomeFragment();
                                    }
                                    transaction.replace(R.id.content, mHomeFragment);
                                    break;
                                case R.id.navigation_me:
                                    //点击第二个Item
                                    //判断登陆状态
                                    switch(state) {
                                        case STUDENT:
                                            if (mMeFragment == null) {
                                                mMeFragment = new MeFragment();
                                            }
                                            transaction.replace(R.id.content, mMeFragment);
                                            break;
                                        case WORKER:
                                            if (mWorkerMeFragment == null) {
                                                mWorkerMeFragment = new WorkerMeFragment();
                                            }
                                            transaction.replace(R.id.content, mWorkerMeFragment);
                                            break;
                                        case ADMIN:
                                            if (mOperationHistoryFragment == null) {
                                                mOperationHistoryFragment = new OperationHistoryFragment();
                                            }
                                            transaction.replace(R.id.content, mOperationHistoryFragment);
                                            break;
                                        case NOLOGIN:
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
                                            transaction.replace(R.id.content, mFavouriteFragment);
                                            break;
                                        case WORKER:
                                            if (mWorkerWorkFragment == null) {
                                                mWorkerWorkFragment = new WorkerWorkFragment();
                                            }
                                            transaction.replace(R.id.content, mWorkerWorkFragment);
                                            break;
                                        case ADMIN:
                                            if (mAdministratorFragment == null) {
                                                mAdministratorFragment = new AdministratorFragment();
                                            }
                                            transaction.replace(R.id.content, mAdministratorFragment);
                                            break;
                                        case NOLOGIN:
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

    //设置初始Fragment
    private void setDefaultFragment() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        mHomeFragment = new HomeFragment();
        transaction.replace(R.id.content,mHomeFragment);
        transaction.commit();
    }

    //显示“系统”菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //设置“系统”菜单按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SystemSettingsActivity.class);
                startActivity(intent);
        }
        return true;
    }

}
