package whut.qingxie.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.Item.ExperienceItem;
import whut.qingxie.Item.MyHoursItem;
import whut.qingxie.R;
import whut.qingxie.activity.MyResumeActivity;
import whut.qingxie.adapter.FavouriteItemAdapter;
import whut.qingxie.adapter.MyExperienceItemAdapter;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.entity.user.Resume;
import whut.qingxie.entity.user.UserExperience;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

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

    private void getExperience() {
        //FIXME:API更改，onFailure处理网络访问错误，onResponse处直接更新UI,favouriteList为收藏的活动列表
        OkhttpUtil.accessData("GET", "/user/3/forks", null, null, new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e(this.getClass().toString(), "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg msg) {
                if (msg != null) {
                    List<Activity4User> favouriteList= (List<Activity4User>) msg.getData().get("UserActivity");
                }

            }
        });
    }
}
