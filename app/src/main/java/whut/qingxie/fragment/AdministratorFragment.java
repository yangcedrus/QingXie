package whut.qingxie.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.iwf.photopicker.PhotoPicker;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import whut.qingxie.R;
import whut.qingxie.activity.ManageWorkerAccountActivity;
import whut.qingxie.activity.ReleaseNoticeActivity;

import static android.app.Activity.RESULT_OK;

public class AdministratorFragment extends Fragment {

    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;

    private CircleImageView circleImageView;

    private Subscription subsInsert;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_administrator,container,false);
    }

    //添加监听注册
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layout1=(RelativeLayout)getActivity().findViewById(R.id.admin_layout1);
        layout2=(RelativeLayout)getActivity().findViewById(R.id.admin_layout2);
        layout3=(RelativeLayout)getActivity().findViewById(R.id.admin_layout3);
        circleImageView=(CircleImageView)getActivity().findViewById(R.id.admin_icon_me);

        layout1.setOnClickListener(new AdministratorFragment.MyListener());
        layout2.setOnClickListener(new AdministratorFragment.MyListener());
        layout3.setOnClickListener(new AdministratorFragment.MyListener());
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第三方加载
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(false)
                        .setPreviewEnabled(false)
                        .start(getActivity());
            }
        });
    }

    //新建监听类
    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()) {
                case R.id.admin_layout1:
                    break;
                case R.id.admin_layout2:
                    intent=new Intent(getActivity(), ManageWorkerAccountActivity.class);
                    startActivity(intent);
                    break;
                case R.id.admin_layout3:
                    intent=new Intent(getActivity(), ReleaseNoticeActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 获得返回的路径
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == PhotoPicker.REQUEST_CODE){
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    insertImagesSync(photos.get(0));
                }
            }
        }
    }

    /**
     * 异步方式插入图片
     * @param data
     */
    private void insertImagesSync(final String data){

        subsInsert = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try{
                    //压缩图片
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }catch (Exception e){
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        })
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String imagePath) {
                        Glide.with(getContext()).load(imagePath).fitCenter().into(circleImageView);
                    }
                });
    }

}
