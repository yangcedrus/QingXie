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
import whut.qingxie.fragment.*;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private int state=0;

    //返回登录者身份信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 1:
                state=data.getIntExtra("data_return",0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private HomeFragment mHomeFragment;
    private MeFragment mMeFragment;
    private FavouriteFragment mFavouriteFragment;

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
                        if(state==0&&item.getItemId()!=R.id.navigation_home){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent, 1);
                        }else{
                            switch(item.getItemId()){
                                case R.id.navigation_home:
                                    if (mHomeFragment == null) {
                                        mHomeFragment = new HomeFragment();
                                    }
                                    transaction.replace(R.id.content, mHomeFragment);
                                    break;
                                case R.id.navigation_me:
                                    if (mMeFragment == null) {
                                        mMeFragment = new MeFragment();
                                    }
                                    transaction.replace(R.id.content, mMeFragment);
                                    break;
                                case R.id.navigation_favourite:
                                    if(mFavouriteFragment==null){
                                        mFavouriteFragment=new FavouriteFragment();
                                    }
                                    transaction.replace(R.id.content, mFavouriteFragment);
                                    break;
                            }
                        }
                        //在登录之前不更新Fragment
                        if(state==0){
                            return true;
                        }else{
                            transaction.commit();
                        }
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
                Intent intent = new Intent(MainActivity.this, SystemSeetingsActivity.class);
                startActivity(intent);
        }
        return true;
    }

}
