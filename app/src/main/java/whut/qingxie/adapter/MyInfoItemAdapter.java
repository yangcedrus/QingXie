package whut.qingxie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.Item.MyInfoItem;
import whut.qingxie.R;

// TODO: 2018/4/8 待删除 
public class MyInfoItemAdapter extends ArrayAdapter {
    private int resourceId;

    public MyInfoItemAdapter(Context context, int textViewResourceId, List<MyInfoItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyInfoItem myInfo = (MyInfoItem) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView1 = (TextView) view.findViewById(R.id.my_info_text1);
        TextView textView2 = (TextView) view.findViewById(R.id.my_info_text2);
        textView1.setText(myInfo.getS1());
        textView2.setText(myInfo.getS2());


        return view;
    }
}
