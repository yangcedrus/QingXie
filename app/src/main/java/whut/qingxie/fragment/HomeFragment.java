package whut.qingxie.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
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

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.CardActivityItemAdapter;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.VolActivityInfo;

import static android.content.ContentValues.TAG;

//主页页面
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private static List<VolActivityInfo> cardActivityItems = new ArrayList<>();
    private static List<String> pictureURLS=new ArrayList<>();

    private View view;
    private static RecyclerView recyclerView;
    private static LinearLayoutManager linearLayoutManager;
    private static CardActivityItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;
    private static View headerView;

    private static String SERVE_URL="http://123.207.87.34:8080/";
    private static String GET_ALL_ACTIVITIES="activity/getAllActivities";
    private static String GET_PICTURES_URLS="";

    //存放图片组,文字组
    private static int picNum=4;
    private ViewPager vp;
    private LinearLayout point;
    private ArrayList<ImageView> imageViews;
    private ArrayList<String> strings;
    private TextView textView;
    private int lastPosition;
    private boolean isRunning=false;// 是否自动轮询

    @SuppressLint("HandlerLeak")
    public static Handler eHandler=new Handler(){
        public void handleMessage(Message message){
            super.handleMessage(message);
            try {
                Msg msg=Msg.parseMapFromJson(message.obj, Content.CLAZZ_MAP);

            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleMessage: "+e.getMessage());
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);

        smartRefreshLayout=(SmartRefreshLayout) view.findViewById(R.id.home_refresh);
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
                    cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                            "2",0,4.0,2.0,10,
                            "东院敬老院","东院敬老院活动，打扫卫生",
                            "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
                }

                //结束加载更多
                if(cardActivityItems.size()<10)
                    smartRefreshLayout.finishLoadmore();
                else
                    smartRefreshLayout.finishLoadmoreWithNoMoreData();
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
        headerView=LayoutInflater.from(getActivity()).inflate(R.layout.item_home_header,recyclerView,false);
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
    }

    //初始化列表数据
    private static void init(){
        cardActivityItems.clear();
        for(int i=0;i<5;i++){
            cardActivityItems.add(new VolActivityInfo(1,"敬老院活动",1,
                    "2",0,4.0,2.0,10,
                    "东院敬老院","东院敬老院活动，打扫卫生","详情详情详情详情详情详情详情详情详情详情详情详" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情" +
                    "情详情详情详情详情详情详情详情详情"));
        }
        smartRefreshLayout.resetNoMoreData();
        reFresh();

        //请求五个活动信息
        // TODO: 2018/3/7 从本地读取
        //requestForFiveActivity();
    }

    //请求五个活动信息
    public static void requestForFiveActivity(){

    }

    //请求首页图片信息
    public static void requestForPicturesURLS(){

    }

    //初始化视图
    private void initViews(){
        point=(LinearLayout)headerView.findViewById(R.id.home_viewPager_point);
        vp=(ViewPager)headerView.findViewById(R.id.home_viewPager);
        textView=(TextView)headerView.findViewById(R.id.home_viewPager_textView);
        vp.setOnPageChangeListener(this);
    }

    //初始化数据
    private void initData(){
        imageViews=new ArrayList<>();
        strings=new ArrayList<>();
        ImageView imageView;
        String s;
        View pointView;
        for(int i=0;i<picNum;i++){
            imageView=new ImageView(getContext());
            if(i%2==0){
                //Glide.with(this).load(pictureURLS.get(i)).centerCrop().into(imageView);
                //消除判断
                imageView.setImageResource(R.drawable.ic_home_black_24dp);
                s="概况1";
            } else{
                imageView.setImageResource(R.drawable.ic_detail_black_24dp);
                s="概况2";
            }
            imageViews.add(imageView);
            strings.add(s);

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
        textView.setText(strings.get(0));
        lastPosition=0;
        vp.setAdapter(new MyPagerAdapter());
        vp.setCurrentItem(picNum*1000);   //防止访问到边界崩溃
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
        textView.setText(strings.get(newPosition));
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
