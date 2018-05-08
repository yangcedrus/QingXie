package whut.qingxie.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.activity.MainActivity;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.adapter.CardActivityItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.dto.PageInfo;
import whut.qingxie.entity.activity.HomepagePicture;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

/**
 * 主页页面
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private static List<VolActivityInfo> cardActivityItems = new ArrayList<>();
    private static List<HomepagePicture> homepagePictures=new ArrayList<>();

    private static int totalPage = Integer.MAX_VALUE; //页总数
    private static int page = 1;    //当前页
    private static int SIZE = 5;  //每页

    private View view;
    private static RecyclerView recyclerView;
    private static LinearLayoutManager linearLayoutManager;
    private static CardActivityItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;
    private static View headerView;
    private static TextView notice;

    //存放图片组,文字组
    private static int picNum = 1;
    private static ViewPager vp;
    private static LinearLayout point;
    private static ArrayList<ImageView> imageViews;
    private static TextView textView;
    private static int lastPosition;
    private static boolean isRunning = false;// 是否自动轮询

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((TextView) getActivity().findViewById(R.id.toolbar_app_name)).setText(R.string.app_name);

        view = inflater.inflate(R.layout.fragment_home, container, false);

        //显示公告
        notice = (TextView) view.findViewById(R.id.home_scroll_text);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.home_refresh);

        if (notice != null) {
            notice.setText("奔走相告！掌上理工大发布新版本啦，大家快来围观啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦！");
            notice.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) smartRefreshLayout.getLayoutParams();
            layoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.scroll_text_height), 0, 0);
        }

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //init();
                //调用服务器数据
                page = 1;
                getSomeActivity();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getMoreActivity();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.activity_card_recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CardActivityItemAdapter(cardActivityItems, getContext());
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_header, recyclerView, false);
        adapter.setHeaderView(headerView);
        recyclerView.setAdapter(adapter);

        initViews();
        if (cardActivityItems.size() == 0) {
            page = 1;
            getSomeActivity();
            requestForPicturesURLS();
        }
    }

    //刷新页面
    public static void reFresh() {
        adapter.notifyDataSetChanged();
    }

    //获取数据
    private void getSomeActivity() {
        cardActivityItems.clear();
        OkhttpUtil.accessData("GET", "/activity/home?page=" + page++ + "&size=" + SIZE, null, null, new CallBackUtil.CallBackMsg() {

            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(getContext(), "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                Log.e("HomeFragment", "onFailure: " + e.getMessage());
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onResponse(Msg response) {
                PageInfo<VolActivityInfo> pageInfo;
                try {
                    pageInfo = PageInfo.parseFromJson((JSONObject) response.getData().get("PageInfo"), VolActivityInfo.class);
                } catch (Exception e) {
                    //解析失败处理
                    Log.d(TAG, "onResponse: " + e.getMessage());
                    Toast.makeText(getActivity(), "读取信息错误，请稍后重试", Toast.LENGTH_SHORT).show();
                    return;
                }

                totalPage = pageInfo.getPages();

                cardActivityItems.addAll(pageInfo.getList());
                //结束刷新
                smartRefreshLayout.resetNoMoreData();
                smartRefreshLayout.finishRefresh();
                reFresh();
            }
        });
    }

    //获取更多
    private void getMoreActivity() {
        OkhttpUtil.accessData("GET", "/activity/home?page=" + page++ + "&size=" + SIZE, null, null, new CallBackUtil.CallBackMsg() {

            @Override
            public void onFailure(Call call, Exception e) {
                //FIXME:还有404等，不全是超时
                Toast.makeText(getContext(), "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                Log.e("HomeFragment", "onFailure: " + e.getMessage());
                //结束刷新
                smartRefreshLayout.finishLoadmore();
            }

            @Override
            public void onResponse(Msg response) {
                PageInfo<VolActivityInfo> pageInfo;
                try {
                    pageInfo = PageInfo.parseFromJson((JSONObject) response.getData().get("PageInfo"), VolActivityInfo.class);
                } catch (Exception e) {
                    //解析失败处理
                    Log.d(TAG, "onResponse: " + e.getMessage());
                    Toast.makeText(getActivity(), "读取信息错误，请稍后重试", Toast.LENGTH_SHORT).show();
                    return;
                }
                totalPage = pageInfo.getPages();

                cardActivityItems.addAll(pageInfo.getList());
                //结束加载更多
                if (page <= totalPage)
                    smartRefreshLayout.finishLoadmore();
                else
                    smartRefreshLayout.finishLoadmoreWithNoMoreData();
                reFresh();
            }
        });
    }

    //请求首页图片信息
    public static void requestForPicturesURLS() {
//        OkhttpUtil.okHttpGet("/activity/getHomePagePic", new CallBackUtil.CallBackString() {
//            @Override
//            public void onFailure(Call call, Exception e) {
//                Toast.makeText(MainActivity.activity, "图片请求失败", Toast.LENGTH_SHORT).show();
//                initData();
//                initAdapter();
//            }
//
//            @Override
//            public void onResponse(String response) {
//                JSONObject object=JSONObject.parseObject(response);
//                JSONArray array=object.getJSONArray("data");
//                String json=JSONObject.toJSONString(array, SerializerFeature.WriteClassName);
//                homepagePictures=JSONObject.parseArray(json,HomepagePicture.class);
//
//            }
//        });

        OkhttpUtil.okHttpGet("/activity/getHomePagePic", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(MainActivity.activity, "图片请求失败", Toast.LENGTH_SHORT).show();
                initData();
                initAdapter();
            }

            @Override
            public void onResponse(Msg response) {
                if (response.getStatus().equals("success")) {
                    homepagePictures = (List<HomepagePicture>) response.getData().get("HomePagePictureList");
                    picNum = homepagePictures.size();
                    setData();
                    initAdapter();
                } else {
                    Toast.makeText(MainActivity.activity, "图片请求失败", Toast.LENGTH_SHORT).show();
                    initData();
                    initAdapter();
                }
            }
        });
    }

    //初始化视图
    private void initViews() {
        point = (LinearLayout) headerView.findViewById(R.id.home_viewPager_point);
        vp = (ViewPager) headerView.findViewById(R.id.home_viewPager);
        textView = (TextView) headerView.findViewById(R.id.home_viewPager_textView);
        //TODO 触摸屏幕停止轮播
        vp.setOnPageChangeListener(this);
    }

    //设置网页返回的数据
    private static void setData() {
        if(homepagePictures.size()==0)
            return;
        Context mContext = MainActivity.activity;
        imageViews = new ArrayList<>();
        ImageView imageView;
        View pointView;
        for (int i = 0; i < picNum; i++) {
            imageView = new ImageView(mContext);
            Glide.with(mContext).load(Content.getServerHost() + homepagePictures.get(i).getHomePagePic()).fitCenter().into(imageView);
            imageViews.add(imageView);

            //加点
            pointView = new View(mContext);
            pointView.setBackgroundResource(R.drawable.selector_point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(8, 8);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false);
            point.addView(pointView, layoutParams);
        }
    }

    //初始化数据
    private static void initData() {
        Context mContext = MainActivity.activity;
        imageViews = new ArrayList<>();
        ImageView imageView;
        String s;
        View pointView;
        HomepagePicture homepagePictureNULL=new HomepagePicture();
        homepagePictureNULL.setGeneral("");
        homepagePictureNULL.setHomePagePic(null);
        homepagePictures.add(homepagePictureNULL);

        imageView = new ImageView(mContext);
        Glide.with(mContext).load(Content.getServerHost() + homepagePictures.get(0).getHomePagePic()).fitCenter().into(imageView);
        imageViews.add(imageView);

        //加点
        pointView = new View(mContext);
        pointView.setBackgroundResource(R.drawable.selector_point);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(8, 8);
        layoutParams.leftMargin = 10;
        pointView.setEnabled(false);
        point.addView(pointView, layoutParams);

    }

    //初始化适配器
    private static void initAdapter() {
        point.getChildAt(0).setEnabled(true);
        textView.setText(homepagePictures.get(0).getGeneral());
        vp.setCurrentItem(picNum * 1000);   //防止访问到边界崩溃
        lastPosition = 0;
        vp.setAdapter(new MyPagerAdapter());
    }

    //自定义适配器
    static class MyPagerAdapter extends PagerAdapter {

        //返回总条数，设置为最大整数
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final int newPosition = position % picNum;//   共有NUM张图片
            if (imageViews.get(newPosition).getParent() != null) {
                container.removeView(imageViews.get(newPosition));
            }
            imageViews.get(newPosition).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context mContext=MainActivity.activity;
                    Intent intent = new Intent(mContext, SignUpActivity.class);
                    intent.putExtra("activity_details", homepagePictures.get(newPosition).getActivityId());
                    mContext.startActivity(intent);
                }
            });
            container.addView(imageViews.get(newPosition));
            return imageViews.get(newPosition);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            int newPosition = position % picNum;//   共有NUM张图片
            container.removeView(imageViews.get(newPosition));
        }
    }

    //页面滑动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //新的页面被选中
    @Override
    public void onPageSelected(int position) {
        int newPosition = position % picNum;
        textView.setText(homepagePictures.get(newPosition).getGeneral());
        point.getChildAt(lastPosition).setEnabled(false);
        point.getChildAt(newPosition).setEnabled(true);
        lastPosition = newPosition;
    }

    //页面滑动状态改变
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //开始轮询
    @Override
    public void onStart() {
        super.onStart();

        //开始轮询
        new Thread() {
            @Override
            public void run() {
                isRunning = true;
                //防止切换Activity时出错
                Activity activity = getActivity();
                while (isRunning) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(vp.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();
    }

    //界面不可见停止轮询
    @Override
    public void onPause() {
        super.onPause();
        isRunning = false;
    }

    //界面销毁停止轮询
    @Override
    public void onDestroy() {
        super.onDestroy();
        cardActivityItems.clear();
        isRunning = false;
    }
}
