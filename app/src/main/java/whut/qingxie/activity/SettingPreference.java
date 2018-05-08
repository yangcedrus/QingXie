package whut.qingxie.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import whut.qingxie.R;
import whut.qingxie.fragment.SysNewMessage;

public class SettingPreference extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            Drawable drawable = getResources().getDrawable(R.drawable.toolbar_background);
            getSupportActionBar().setBackgroundDrawable(drawable);
            getSupportActionBar().setTitle("");
        }
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", 0);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (flag) {
            case 1:
                SysNewMessage fragmentPreferences = new SysNewMessage();
                transaction.replace(android.R.id.content, fragmentPreferences);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                break;
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
