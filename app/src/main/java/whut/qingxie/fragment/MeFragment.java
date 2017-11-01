package whut.qingxie.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.MyItem;
import whut.qingxie.adapter.MyItemAdapter;

public class MeFragment extends Fragment {

    private List<MyItem> myItemList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //创建ListView
        initItems();
        MyItemAdapter adapter=new MyItemAdapter(getActivity(), R.layout.my_item,myItemList);
        View view = inflater.inflate(R.layout.activity_mefragment, container, false);
        ListView listView=(ListView) view.findViewById(R.id.me_list_view);
        listView.setAdapter(adapter);

        return view;
    }

    private void initItems(){
        if(myItemList.size()!=0)
            return;
        myItemList.add(new MyItem("查看我的简历"));
        myItemList.add(new MyItem("我的志愿活动"));
        myItemList.add(new MyItem("我的志愿工时"));
        myItemList.add(new MyItem("设置个人信息"));

    }
}

