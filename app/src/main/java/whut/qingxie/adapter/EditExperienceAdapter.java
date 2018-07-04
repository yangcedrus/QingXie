package whut.qingxie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.entity.user.UserExperience;

public class EditExperienceAdapter extends ArrayAdapter {
    private int resourceId;

    public EditExperienceAdapter(Context context, int textViewResourceId, List<UserExperience> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserExperience userExperience = (UserExperience) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.experience_begin_time);
        TextView textView1 = (TextView) view.findViewById(R.id.experience_end_time);
        TextView textView2 = (TextView) view.findViewById(R.id.experience_name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String days = sdf.format(userExperience.getBegin());
        textView.setText(days);
        days = sdf.format(userExperience.getEnd());
        textView1.setText(days);
        textView2.setText(userExperience.getActivityName());
        return view;
    }
}
