package whut.qingxie.fragment;

import android.app.Activity;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.adapter.CardActivityItemAdapter;
import whut.qingxie.Item.CardActivityItem;

//主页页面
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private List<CardActivityItem> cardActivityItems = new ArrayList<>();
    private View view;

    //存放图片组
    private int picNum=4;
    private ViewPager vp;
    private LinearLayout point;
    private ArrayList<ImageView> imageViews;
    private int lastPosition;
    private boolean isRunning=false;// 是否自动轮询

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化轮询控件信息
        initViews();
        initData();
        initAdapter();

        if(cardActivityItems.size()==0)
            init();
        RecyclerView recyclerView=(RecyclerView) getActivity().findViewById(R.id.activity_card_recyclerView);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        CardActivityItemAdapter adapter=new CardActivityItemAdapter(cardActivityItems);
        recyclerView.setAdapter(adapter);
    }

    private void init(){
        cardActivityItems.add(new CardActivityItem("000001","武汉理工大学管理学院","随便什么计划1",1,false));
        cardActivityItems.add(new CardActivityItem("000002","武汉理工大学管理学院","随便什么计划2",1,true));
        cardActivityItems.add(new CardActivityItem("000003","武汉理工大学管理学院","随便什么计划3",1,false));
        cardActivityItems.add(new CardActivityItem("000004","武汉理工大学管理学院","随便什么计划4",1,true));
        cardActivityItems.add(new CardActivityItem("000005","武汉理工大学管理学院","随便什么计划5",1,false));
        cardActivityItems.add(new CardActivityItem("000006","武汉理工大学管理学院","随便什么计划6",1,true));
    }

    //初始化视图
    private void initViews(){
        point=(LinearLayout)view.findViewById(R.id.home_viewPager_point);
        vp=(ViewPager)view.findViewById(R.id.home_viewPager);
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
                Glide.with(this).load("http://123.207.87.34:8080/qingxie-img/394458467239727056.jpg").centerCrop().into(imageView);
            else
                Glide.with(this).load("http://123.207.87.34:8080/qingxie-img/745230278946470272.jpg").centerCrop().into(imageView);
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
