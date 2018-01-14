package whut.qingxie.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import whut.qingxie.R;
import whut.qingxie.activity.ReleaseNoticeActivity;

public class AdministratorFragment extends Fragment {

    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_administrator_fragment,container,false);
    }

    //添加监听注册
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layout1=(LinearLayout)getActivity().findViewById(R.id.admin_layout1);
        layout2=(LinearLayout)getActivity().findViewById(R.id.admin_layout2);
        layout3=(LinearLayout)getActivity().findViewById(R.id.admin_layout3);

        layout1.setOnClickListener(new AdministratorFragment.MyListener());
        layout2.setOnClickListener(new AdministratorFragment.MyListener());
        layout3.setOnClickListener(new AdministratorFragment.MyListener());
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
                    break;
                case R.id.admin_layout3:
                    intent=new Intent(getActivity(), ReleaseNoticeActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
