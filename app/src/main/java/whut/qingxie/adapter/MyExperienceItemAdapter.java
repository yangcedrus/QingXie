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

import whut.qingxie.R;
import whut.qingxie.Item.ExperienceItem;

/**
 *
 * MyResume个人简历页面
 * 我的志愿经历witem适配器
 */
public class MyExperienceItemAdapter extends ArrayAdapter {
    private int resourceId;

    public MyExperienceItemAdapter(Context context, int textViewResourceId, List<ExperienceItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExperienceItem experienceItem=(ExperienceItem)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView=(TextView)view.findViewById(R.id.experience_time);
        TextView textView2=(TextView)view.findViewById(R.id.experience_name);
        textView.setText(experienceItem.getTime());
        textView2.setText(experienceItem.getName());
        return view;
    }
}
