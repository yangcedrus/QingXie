package whut.qingxie.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import whut.qingxie.Item.MyHoursItem;
import whut.qingxie.R;
import whut.qingxie.adapter.FavouriteItemAdapter;
import whut.qingxie.entity.activity.VolActivityInfo;

public class FavouriteFragment extends Fragment {

    private List<VolActivityInfo> favouriteItems=new ArrayList<>();

    private static SmartRefreshLayout smartRefreshLayout;
    private static FavouriteItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView=(RecyclerView)getActivity().findViewById(R.id.favourite_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new FavouriteItemAdapter(favouriteItems);
        recyclerView.setAdapter(adapter);

        //refresh监听
        smartRefreshLayout=(SmartRefreshLayout) getActivity().findViewById(R.id.favourite_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                init();
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for(int i=0;i<5;i++){
                    VolActivityInfo ac=new VolActivityInfo(1,"敬老院活动",1,
                            "2",0,4.0,2.0,10,
                            "东院敬老院","东院敬老院活动，打扫卫生",null);
                    ac.setCreateTime(new Date());
                    favouriteItems.add(ac);
                }
                //结束加载更多
                if(favouriteItems.size()<10)
                    smartRefreshLayout.finishLoadmore();
                else
                    smartRefreshLayout.finishLoadmoreWithNoMoreData();
                reFresh();
            }
        });
        if(favouriteItems.size()==0)
            init();
    }

    private static void reFresh(){
        adapter.notifyDataSetChanged();
    }

    public void init() {
        favouriteItems.clear();
        for (int i = 0; i < 5; i++) {
            VolActivityInfo ac=new VolActivityInfo(1,"敬老院活动",1,
                    "2",0,4.0,2.0,10,
                    "东院敬老院","东院敬老院活动，打扫卫生",null);
            ac.setCreateTime(new Date());
            favouriteItems.add(ac);
        }
        reFresh();
        smartRefreshLayout.resetNoMoreData();
    }
}
