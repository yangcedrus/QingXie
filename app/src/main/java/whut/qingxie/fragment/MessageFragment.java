package whut.qingxie.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import whut.qingxie.R;

public class MessageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: 2017/9/20
        //创建新的类显示消息，以及聊天室如何创建
        return inflater.inflate(R.layout.activity_messagefragment,container,false);
    }
}
