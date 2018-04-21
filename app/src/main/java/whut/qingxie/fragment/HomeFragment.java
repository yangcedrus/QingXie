package whut.qingxie.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.adapter.CardActivityItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.dto.PageInfo;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

/**
 * 主页页面
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private static List<VolActivityInfo> cardActivityItems = new ArrayList<>();
    private static List<String> pictureURLS = new ArrayList<>();

    private static int totalPage = Integer.MAX_VALUE; //页总数
    private static int page = 1;    //当前页
    private static int SIZE = 5;  //每页

    private View view;
    private static RecyclerView recyclerView;
    private static LinearLayoutManager linearLayoutManager;
    private static CardActivityItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;
    private static View headerView;

    //存放图片组,文字组
    private static int picNum = 4;
    private ViewPager vp;
    private LinearLayout point;
    private ArrayList<ImageView> imageViews;
    private ArrayList<String> strings;
    private TextView textView;
    private int lastPosition;
    private boolean isRunning = false;// 是否自动轮询

    @SuppressLint("HandlerLeak")
    public static Handler eHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                Msg msg = Msg.parseMapFromJson(message.obj, Content.getClazzMap());

            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleMessage: " + e.getMessage());
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((TextView)getActivity().findViewById(R.id.toolbar_app_name)).setText(R.string.app_name);

        view = inflater.inflate(R.layout.fragment_home, container, false);

        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.home_refresh);
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

        if (cardActivityItems.size() == 0) {
            page = 1;
            getSomeActivity();
        }


        initViews();
        initData();
        initAdapter();
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

    }

    //初始化视图
    private void initViews() {
        point = (LinearLayout) headerView.findViewById(R.id.home_viewPager_point);
        vp = (ViewPager) headerView.findViewById(R.id.home_viewPager);
        textView = (TextView) headerView.findViewById(R.id.home_viewPager_textView);
//        //触摸屏幕停止轮询，有bug
//        vp.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case ACTION_DOWN:isRunning=false;break;
//                    case ACTION_UP:isRunning=true;break;
//                }
//                return false;
//            }
//        });
        vp.setOnPageChangeListener(this);
    }

    //初始化数据
    private void initData() {
        imageViews = new ArrayList<>();
        strings = new ArrayList<>();
        ImageView imageView;
        String s;
        View pointView;
        for (int i = 0; i < picNum; i++) {
            imageView = new ImageView(getContext());
            if (i % 2 == 0) {
                //Glide.with(this).load(pictureURLS.get(i)).centerCrop().into(imageView);
                s = "概况1";
            } else {
                s = "概况2";
            }
            imageViews.add(imageView);
            strings.add(s);

            //加点
            pointView = new View(getContext());
            pointView.setBackgroundResource(R.drawable.selector_point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(8, 8);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false);
            point.addView(pointView, layoutParams);
        }
    }

    //初始化适配器
    private void initAdapter() {
        point.getChildAt(0).setEnabled(true);
        textView.setText(strings.get(0));
        lastPosition = 0;
        vp.setAdapter(new MyPagerAdapter());
        vp.setCurrentItem(picNum * 1000);   //防止访问到边界崩溃
    }

    //自定义适配器
    class MyPagerAdapter extends PagerAdapter {

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
            int newPosition = position % picNum;//   共有3张图片
            if (imageViews.get(newPosition).getParent() != null) {
                container.removeView(imageViews.get(newPosition));
            }
            container.addView(imageViews.get(newPosition));
            return imageViews.get(newPosition);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            int newPosition = position % picNum;//   共有3张图片
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
        textView.setText(strings.get(newPosition));
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
                        Thread.sleep(3000);
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
        isRunning = false;
    }
}
