package whut.qingxie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.adapter.FavouriteItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

/**
 * 学生收藏页面
 */
public class FavouriteFragment extends Fragment {

    private List<Activity4User> favouriteItems=new ArrayList<>();

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
        if(favouriteItems.size()==0)
            init();
    }

    private static void reFresh(){
        adapter.notifyDataSetChanged();
    }

    public void init() {
        favouriteItems.clear();
        OkhttpUtil.okHttpGet("/activity/" + Content.getUserId() + "/forks", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(getContext(),"连接超时，请检查网络连接",Toast.LENGTH_LONG).show();
                Log.e("HomeFragment", "onFailure: " + e.getMessage());
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onResponse(Msg response) {
                List<Activity4User> activityInfo=(List<Activity4User>) response.getData().get("UserActivityList");

                favouriteItems.addAll(activityInfo);
                //结束刷新
                smartRefreshLayout.resetNoMoreData();
                smartRefreshLayout.finishRefresh();
                reFresh();
            }
        });
    }

//    private void getExperience() {
//        //FIXME:API更改，onFailure处理网络访问错误，onResponse处直接更新UI,favouriteList为收藏的活动列表
//        OkhttpUtil.accessData("GET", "/user/3/forks", null, null, new CallBackUtil.CallBackMsg() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                Log.e(this.getClass().toString(), "onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Msg msg) {
//                if (msg != null) {
//                    List<Activity4User> favouriteList= (List<Activity4User>) msg.getData().get("UserActivity");
//                }
//
//            }
//        });
//    }
}
