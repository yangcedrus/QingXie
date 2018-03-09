package whut.qingxie.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.adapter.CardActivityItemAdapter;
import whut.qingxie.Item.CardActivityItem;
import whut.qingxie.bean.VolActivityInfo;
import whut.qingxie.network.HttpUtil;
import whut.qingxie.network.parseJSON;

import static whut.qingxie.network.sendRequest.requestForFiveActivity;

//主页页面
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private static List<VolActivityInfo> cardActivityItems = new ArrayList<>();
    private View view;
    private static RecyclerView recyclerView;
    private static LinearLayoutManager linearLayoutManager;
    private static CardActivityItemAdapter adapter;
    private static SwipeRefreshLayout swipeRefresh;
    private static View headerView;

    private static String SERVE_URL="http://123.207.87.34:8080/";
    private static String GET_ALL_ACTIVITIES="activity/getAllActivities";

    @SuppressLint("HandlerLeak")
    private static Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0)
            {
                cardActivityItems.addAll(parseJSON.parseActivity(msg.obj.toString()));
                reFresh();
            }else{
                Log.d("HomeFragment","message error");
            }
        }
    };

    //存放图片组
    private int picNum=4;
    private ViewPager vp;
    private LinearLayout point;
    private ArrayList<ImageView> imageViews;
    private int lastPosition;
    private boolean isRunning=false;// 是否自动轮询

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);

        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.home_refresh);
        swipeRefresh.setColorSchemeColors(Color.BLUE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //requestForFiveActivity();
                reFresh();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView=(RecyclerView) view.findViewById(R.id.activity_card_recyclerView);
        linearLayoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CardActivityItemAdapter(cardActivityItems);
        headerView=LayoutInflater.from(getActivity()).inflate(R.layout.home_header,recyclerView,false);
        adapter.setHeaderView(headerView);
        recyclerView.setAdapter(adapter);

        if(cardActivityItems.size()==0)
            init();

        initViews();
        initData();
        initAdapter();
    }

    //刷新页面
    public static void reFresh(){
        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

    //初始化列表数据
    private static void init(){


        cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                "2",0,4,2,10,
                "东院敬老院","东院敬老院活动，打扫卫生",null,
                "2018-3-30 11:11:11",null,null,null,
                null,null));
        reFresh();

        //请求五个活动信息
        // TODO: 2018/3/7 从本地读取
        //requestForFiveActivity();
    }

    //请求五个活动信息
    public static void requestForFiveActivity(){
        HttpUtil.sendOkHttpRequest(SERVE_URL + GET_ALL_ACTIVITIES, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=mHandler.obtainMessage();
                message.what=404;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();

                Message message=mHandler.obtainMessage();
                if(response.code()==200){
                    message.obj=responseData;
                }else{
                    message.what=response.code();
                }
                mHandler.sendMessage(message);

            }
        });
    }

    //初始化视图
    private void initViews(){
        point=(LinearLayout)headerView.findViewById(R.id.home_viewPager_point);
        vp=(ViewPager)headerView.findViewById(R.id.home_viewPager);
        vp.setOnPageChangeListener(this);
    }

    //初始化数据
    private void initData(){
        imageViews=new ArrayList<>();
        ImageView imageView;
        View pointView;
        for(int i=0;i<4;i++){
            imageView=new ImageView(getContext());
            if(i%2==0)
                //Glide.with(this).load("http://123.207.87.34:8080/qingxie-img/394458467239727056.jpg").centerCrop().into(imageView);
                imageView.setImageResource(R.drawable.ic_home_black_24dp);
            else
                //Glide.with(this).load("http://123.207.87.34:8080/qingxie-img/745230278946470272.jpg").centerCrop().into(imageView);
                imageView.setImageResource(R.drawable.ic_detail_black_24dp);
            imageViews.add(imageView);

            //加点
            pointView=new View(getContext());
            pointView.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(8,8);
            if(i!=0){
                layoutParams.leftMargin=10;
            }
            pointView.setEnabled(false);
            point.addView(pointView,layoutParams);
        }
    }

    //初始化适配器
    private void initAdapter(){
        point.getChildAt(0).setEnabled(true);
        lastPosition=0;
        vp.setAdapter(new MyPagerAdapter());
        vp.setCurrentItem(40000);
    }

    //自定义适配器
    class MyPagerAdapter extends PagerAdapter{

        //返回总条数，设置为最大整数
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition =position%picNum;//   共有3张图片
            ImageView imageView=imageViews.get(newPosition);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    //页面滑动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //新的页面被选中
    @Override
    public void onPageSelected(int position) {
        int newPosition=position%picNum;
        point.getChildAt(lastPosition).setEnabled(false);
        point.getChildAt(newPosition).setEnabled(true);
        lastPosition=newPosition;
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
        new Thread(){
            @Override
            public void run() {
                isRunning=true;
                //防止切换Activity时出错
                Activity activity=getActivity();
                while(isRunning){
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(vp.getCurrentItem()+1);
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
        isRunning=false;
    }

    //界面销毁停止轮询
    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }
}
