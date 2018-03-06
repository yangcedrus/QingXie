package whut.qingxie.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import whut.qingxie.R;
import whut.qingxie.activity.MyHoursActivity;
import whut.qingxie.activity.MyInfoActivity;
import whut.qingxie.activity.MyMessageActivity;
import whut.qingxie.activity.MyResumeActivity;
import whut.qingxie.activity.MyServiceActivity;

public class MeFragment extends Fragment {

    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private RelativeLayout layout5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);
    }

    //添加监听注册
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layout1=(RelativeLayout)getActivity().findViewById(R.id.me_layout1);
        layout2=(RelativeLayout)getActivity().findViewById(R.id.me_layout2);
        layout3=(RelativeLayout)getActivity().findViewById(R.id.me_layout3);
        layout4=(RelativeLayout)getActivity().findViewById(R.id.me_layout4);
        layout5=(RelativeLayout)getActivity().findViewById(R.id.me_layout5);

        layout1.setOnClickListener(new MyListener());
        layout2.setOnClickListener(new MyListener());
        layout3.setOnClickListener(new MyListener());
        layout4.setOnClickListener(new MyListener());
        layout5.setOnClickListener(new MyListener());
    }

    //新建监听类
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.me_layout1:
                    intent=new Intent(getActivity(), MyResumeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_layout2:
                    intent=new Intent(getActivity(), MyMessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_layout3:
                    intent=new Intent(getActivity(), MyServiceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_layout4:
                    intent=new Intent(getActivity(), MyHoursActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_layout5:
                    intent=new Intent(getActivity(), MyInfoActivity.class);
                    startActivity(intent);
                    break;

            }
        }

    }

}

