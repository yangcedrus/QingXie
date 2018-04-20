package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.entity.activity.Activity4User;

/**
 * MyServiceActivity
 * 我的志愿服务
 */
public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.ViewHolder> {

    public List<Activity4User> activity4Users;
    private Context mContext;

    public ServiceItemAdapter(List<Activity4User> activity4UserList) {
        activity4Users = activity4UserList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_vol_services, parent, false);
        final ViewHolder holder;
        holder = new ViewHolder(view);
        holder.serviceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity4User item = activity4Users.get(holder.getLayoutPosition());
                if (mContext == null)
                    mContext = parent.getContext();
                Intent intent = new Intent(mContext, SignUpActivity.class);
                intent.putExtra("activity_details", item.getActivityId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity4User activityInfo = activity4Users.get(position);
        holder.name.setText(activityInfo.getName());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.time.setText(sdf.format(activityInfo.getCreateTime()));
        holder.num.setText(Integer.toString(position + 1));
        holder.info.setText(activityInfo.getProfile());
        holder.status.setText(activityInfo.getStatus());
        switch (activityInfo.getStatus()) {
            case "报名中":
                holder.status.setCompoundDrawables(holder.drawable0, null, null, null);
                break;
            case "报名失败":
                holder.status.setCompoundDrawables(holder.drawable0, null, null, null);
                break;
            case "面试中":
                holder.status.setCompoundDrawables(holder.drawable1, null, null, null);
                break;
            case "面试失败":
                holder.status.setCompoundDrawables(holder.drawable1, null, null, null);
                break;
            case "进行中":
                holder.status.setCompoundDrawables(holder.drawable2, null, null, null);
                break;
            case "已完成":
                holder.status.setCompoundDrawables(holder.drawable3, null, null, null);
                break;
            default:
                holder.status.setCompoundDrawables(holder.drawable3, null, null, null);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return activity4Users.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, num, info, status;
        Drawable drawable0, drawable1, drawable2, drawable3;
        View serviceView;

        private ViewHolder(View view) {
            super(view);
            serviceView = view;
            name = (TextView) view.findViewById(R.id.service_name);
            time = (TextView) view.findViewById(R.id.service_time);
            num = (TextView) view.findViewById(R.id.service_num);
            info = (TextView) view.findViewById(R.id.service_info);
            status = (TextView) view.findViewById(R.id.service_status);
            drawable0 = view.getResources().getDrawable(R.drawable.ic_signning_up_yellow_24dp);
            drawable1 = view.getResources().getDrawable(R.drawable.ic_metting_green_24dp);
            drawable2 = view.getResources().getDrawable(R.drawable.ic_running_blue_24dp);
            drawable3 = view.getResources().getDrawable(R.drawable.ic_finish_green_24dp);

            drawable0.setBounds(0, 0, drawable0.getMinimumWidth(), drawable0.getMinimumHeight());
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        }
    }
}
