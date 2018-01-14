package whut.qingxie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import whut.qingxie.R;
import whut.qingxie.activity.ManageWorkAvtivity;
import whut.qingxie.activity.ManageWorkTimeActivity;
import whut.qingxie.activity.ReleaseTweetActivity;
import whut.qingxie.activity.ReleaseWorkActivity;

public class WorkerWorkFragment extends Fragment {
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private LinearLayout layout5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_worker_work_fragment,container,false);
    }

    //添加监听注册
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layout1=(LinearLayout)getActivity().findViewById(R.id.worker_me_layout5);
        layout2=(LinearLayout)getActivity().findViewById(R.id.worker_me_layout6);
        layout3=(LinearLayout)getActivity().findViewById(R.id.worker_me_layout7);
        layout4=(LinearLayout)getActivity().findViewById(R.id.worker_me_layout8);
        layout5=(LinearLayout)getActivity().findViewById(R.id.worker_me_layout9);

        layout1.setOnClickListener(new WorkerWorkFragment.MyListener());
        layout2.setOnClickListener(new WorkerWorkFragment.MyListener());
        layout3.setOnClickListener(new WorkerWorkFragment.MyListener());
        layout4.setOnClickListener(new WorkerWorkFragment.MyListener());
        layout5.setOnClickListener(new WorkerWorkFragment.MyListener());
    }

    //新建监听类
    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.worker_me_layout5:
                    intent=new Intent(getActivity(), ReleaseTweetActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout6:
                    intent=new Intent(getActivity(), ManageWorkTimeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout7:
                    intent=new Intent(getActivity(), ManageWorkAvtivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout8:
                    break;
                case R.id.worker_me_layout9:
                    intent=new Intent(getActivity(), ReleaseWorkActivity.class);
                    startActivity(intent);
                    break;
            }
        }

    }
}
