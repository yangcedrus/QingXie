package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.VolunteerSignActivity;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;

public class ManageVolunteerItemAdapter extends RecyclerView.Adapter<ManageVolunteerItemAdapter.ViewHolder> {
    private List<Activity4User> activityInfoList;
    private Context mContext;

    public ManageVolunteerItemAdapter(List<Activity4User> activityInfoList) {
        this.activityInfoList = activityInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_volunteer,parent,false);
        final ViewHolder holder;
        holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getLayoutPosition();
                Activity4User item=activityInfoList.get(pos);
                if (mContext == null)
                    mContext = parent.getContext();
                Intent intent=new Intent(mContext, VolunteerSignActivity.class);
                intent.putExtra("activityID",item.getActivityId());
                intent.putExtra("title",item.getName());
                if(item.getSponsor()!=null)
                    intent.putExtra("sponsor",item.getSponsor());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity4User activityInfo=activityInfoList.get(position);

        holder.title.setText(activityInfo.getName());
        holder.manager.setText(activityInfo.getSponsor());
    }

    @Override
    public int getItemCount() {
        return activityInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,manager;
        View view;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            title=(TextView)view.findViewById(R.id.manage_volunteer_title);
            manager=(TextView)view.findViewById(R.id.manage_volunteer_manager);
        }
    }
}
