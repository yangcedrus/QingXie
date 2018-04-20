package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.user.User;

public class VolunteerSignItemAdapter extends RecyclerView.Adapter<VolunteerSignItemAdapter.ViewHolder> {

    private List<User> volunteerList;

    public VolunteerSignItemAdapter(List<User> volunteerList) {
        this.volunteerList = volunteerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vol_sign, parent, false);
        final ViewHolder holder;
        holder = new ViewHolder(view);
        holder.YES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.YES.setChecked(true);
                holder.NO.setChecked(false);
            }
        });
        holder.NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.YES.setChecked(false);
                holder.NO.setChecked(true);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user=volunteerList.get(position);

        holder.name.setText(user.getName());
        holder.YES.setChecked(false);
        holder.NO.setChecked(false);
    }


    @Override
    public int getItemCount() {
        return volunteerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox YES,NO;
        TextView name;

        private ViewHolder(View view){
            super(view);
            YES=(CheckBox) view.findViewById(R.id.vol_sign_yes);
            NO=(CheckBox) view.findViewById(R.id.vol_sign_no);
            name=(TextView)view.findViewById(R.id.vol_sign_name);
        }
    }
}
