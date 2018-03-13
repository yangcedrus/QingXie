package whut.qingxie.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.FavouriteItemAdapter;
import whut.qingxie.entity.activity.VolActivityInfo;

public class FavouriteFragment extends Fragment {

    private List<VolActivityInfo> favouriteItems=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(favouriteItems.size()==0)
            initItem();
        FavouriteItemAdapter adapter=new FavouriteItemAdapter(getActivity(),R.layout.favourite_item,favouriteItems);
        ListView listView=(ListView)getActivity().findViewById(R.id.favourite_listview);
        listView.setAdapter(adapter);
    }

    public void initItem(){
        favouriteItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        favouriteItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        favouriteItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        favouriteItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        favouriteItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
    }
}
