package whut.qingxie.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import whut.qingxie.R;
import whut.qingxie.activity.ManageWorkAvtivity;
import whut.qingxie.activity.MyHoursActivity;
import whut.qingxie.activity.MyMessageActivity;
import whut.qingxie.activity.MyResumeActivity;
import whut.qingxie.activity.MyServiceActivity;
import whut.qingxie.activity.MyWorkActivity;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * 青协工作人员“我”页面
 */
public class WorkerMeFragment extends Fragment {

    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private RelativeLayout layout5;
    private CircleImageView circleImageView;
    private TextView textView;

    private Subscription subsInsert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worker_me, container, false);
    }

    //添加监听注册
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layout1 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout1);
        layout2 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout2);
        layout3 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout3);
        layout4 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout4);
        layout5 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout5);
        circleImageView = (CircleImageView) getActivity().findViewById(R.id.worker_me_icon_me);
        textView=(TextView)getActivity().findViewById(R.id.worker_name_me);

        layout1.setOnClickListener(new WorkerMeFragment.MyListener());
        layout2.setOnClickListener(new WorkerMeFragment.MyListener());
        layout3.setOnClickListener(new WorkerMeFragment.MyListener());
        layout4.setOnClickListener(new WorkerMeFragment.MyListener());
        layout5.setOnClickListener(new WorkerMeFragment.MyListener());
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

        //设置头像
        String img = Content.getIconAccessPath();
        img = Content.getServerHost() + img;
        Glide.with(getContext()).load(img).fitCenter().into(circleImageView);

        textView.setText(Content.getNAME());
        if (Content.getGENDER().equals("M")) {
            Drawable drawable = getActivity().getResources().getDrawable(R.drawable.ic_man_blue_24dp);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
        } else {
            Drawable drawable = getActivity().getResources().getDrawable(R.drawable.ic_woman_pink_24dp);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
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
                if (requestCode == PhotoPicker.REQUEST_CODE) {
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

                    //上传图片
                    upload_icon(photos.get(0));

                    insertImagesSync(photos.get(0));
                }
            }
        }
    }

    /**
     * 异步方式插入图片
     * @param data
     */
    private void insertImagesSync(final String data) {

        subsInsert = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    //压缩图片
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
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

    /**
     * 上传图片
     *
     * @param imagePath
     */
    private void upload_icon(String imagePath) {
        File file = new File(imagePath);

        if (!file.exists())
            return;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "multipart/form-data; boundary=" + UUID.randomUUID().toString());
        headerMap.put("connection", "keep-alive");
        headerMap.put("user-agent", "android");

        //FIXME 原本Okhttputil无法完成
        OkHttpUtils.post()//
                .addFile("icon", file.getName(), file)//
                .url(Content.getServerHost() + "/user/" + Content.getUserId() + "/icon/update")
                .headers(headerMap)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Msg msg = gson.fromJson(response, Msg.class);
                        if (msg.getStatus().equals("error")) {
                            Toast.makeText(getActivity(), "头像上传失败", Toast.LENGTH_LONG).show();
                        } else {
                            String image = (String) msg.getData().get("iconAccessPath");
                            Content.setIconAccessPath(image);
                        }
                    }
                });
    }

    //新建监听类
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.worker_me_layout1:
                    intent = new Intent(getActivity(), MyResumeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout2:
                    intent = new Intent(getActivity(), MyMessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout3:
                    intent = new Intent(getActivity(), MyServiceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout4:
                    intent = new Intent(getActivity(), MyHoursActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout5:
                    intent = new Intent(getActivity(), MyWorkActivity.class);
                    startActivity(intent);
                    break;
            }
        }

    }
}
