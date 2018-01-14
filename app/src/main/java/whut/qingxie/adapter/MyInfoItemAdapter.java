package whut.qingxie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.bean.MyInfoItem;
import whut.qingxie.bean.MyItem;

public class MyInfoItemAdapter extends ArrayAdapter {
    private int resourceId;

    public MyInfoItemAdapter(Context context, int textViewResourceId, List<MyInfoItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyInfoItem myInfo= (MyInfoItem) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView itemimage=(ImageView)view.findViewById(R.id.my_info_image);
        TextView textView1=(TextView)view.findViewById(R.id.my_info_text1);
        TextView textView2=(TextView)view.findViewById(R.id.my_info_text2);
        itemimage.setImageResource(myInfo.getImage());
        textView1.setText(myInfo.getS1());
        textView2.setText(myInfo.getS2());


        return view;
    }
}
