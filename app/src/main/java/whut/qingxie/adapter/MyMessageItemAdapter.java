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

import whut.qingxie.Item.MyMessageItem;
import whut.qingxie.R;

// TODO: 2018/4/8 待完成 
public class MyMessageItemAdapter extends ArrayAdapter {
    private int resourceId;

    public MyMessageItemAdapter(Context context, int textViewResourceId, List<MyMessageItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyMessageItem myMessageItem = (MyMessageItem) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView title = (TextView) view.findViewById(R.id.message_item_title);
        TextView text = (TextView) view.findViewById(R.id.message_item_text);
        // TODO: 2017/11/18 从服务器下载对方头像
        ImageView image = (ImageView) view.findViewById(R.id.message_item_image);

        title.setText(myMessageItem.getTitle());
        text.setText(myMessageItem.getText());
        image.setImageResource(myMessageItem.getImageView());

        return view;
    }
}
