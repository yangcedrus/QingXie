package whut.qingxie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import whut.qingxie.R;
import whut.qingxie.activity.ManageVolunteerActivity;
import whut.qingxie.activity.ManageWorkAvtivity;
import whut.qingxie.activity.ReleaseWorkActivity;
import whut.qingxie.activity.RichTextEditorActivity;

/**
 * 青协工作人员工作页面
 */
public class WorkerWorkFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((TextView)getActivity().findViewById(R.id.toolbar_app_name)).setText("我的工作");
        return inflater.inflate(R.layout.fragment_worker_work, container, false);
    }

    //添加监听注册
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout layout1 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout5);
        RelativeLayout layout3 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout7);
        RelativeLayout layout4 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout8);
        RelativeLayout layout5 = (RelativeLayout) getActivity().findViewById(R.id.worker_me_layout9);

        layout1.setOnClickListener(new WorkerWorkFragment.MyListener());
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
                    intent = new Intent(getActivity(), RichTextEditorActivity.class);
                    intent.putExtra("title","推文编辑");
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout7:
                    intent = new Intent(getActivity(), ManageWorkAvtivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout8:
                    intent = new Intent(getActivity(), ManageVolunteerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.worker_me_layout9:
                    intent = new Intent(getActivity(), ReleaseWorkActivity.class);
                    startActivity(intent);
                    break;
            }
        }

    }
}
