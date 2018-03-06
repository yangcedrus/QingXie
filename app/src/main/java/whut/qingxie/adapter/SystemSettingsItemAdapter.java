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
import whut.qingxie.Item.SystemSettingsItem;

public class SystemSettingsItemAdapter extends ArrayAdapter {
    private int resourceId;

    public SystemSettingsItemAdapter(Context context, int textViewResourceId, List<SystemSettingsItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SystemSettingsItem systemSettingsItem= (SystemSettingsItem) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView itemimage=(ImageView)view.findViewById(R.id.my_item_image);
        TextView textView=(TextView)view.findViewById(R.id.my_item_text);
        itemimage.setImageResource(systemSettingsItem.getImageId());
        textView.setText(systemSettingsItem.getName());
        return view;
    }
}
