package whut.qingxie.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;

//主页页面
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: 2017/9/20 新建类以及适配器，显示帖子
        return inflater.inflate(R.layout.activity_homefragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //投票按钮响应
        Button button=(Button)getActivity().findViewById(R.id.vote_for);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
