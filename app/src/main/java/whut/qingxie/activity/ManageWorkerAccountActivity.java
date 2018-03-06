package whut.qingxie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.ManageWorkerAccountItemAdapter;
import whut.qingxie.Item.ManageWorkerAccountItem;

public class ManageWorkerAccountActivity extends AppCompatActivity {
    private List<ManageWorkerAccountItem> manageWorkerAccountItemList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker_account);
        if(manageWorkerAccountItemList.size()==0)
            initInfo();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.manage_worker_account_recyclerView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ManageWorkerAccountItemAdapter adapter=new ManageWorkerAccountItemAdapter(manageWorkerAccountItemList);
        recyclerView.setAdapter(adapter);
    }

    private void initInfo(){
        manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
        manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
        manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
        manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
        manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
        manageWorkerAccountItemList.add(new ManageWorkerAccountItem("923","0121","是","2018/01/01"));
    }
}
