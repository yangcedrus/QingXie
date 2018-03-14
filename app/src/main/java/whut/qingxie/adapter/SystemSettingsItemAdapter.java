package whut.qingxie.adapter;

/**
 * 系统设置Adapter
 */

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
        ImageView image=(ImageView)view.findViewById(R.id.system_setting_item_image);
        TextView textView=(TextView)view.findViewById(R.id.system_setting_item_text);
        image.setImageResource(systemSettingsItem.getImageId());
        textView.setText(systemSettingsItem.getName());
        return view;
    }
}
