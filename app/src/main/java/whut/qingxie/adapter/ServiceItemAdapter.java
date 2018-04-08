package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.entity.activity.VolActivityInfo;

/**
 * MyServiceActivity
 * 我的志愿服务
 */
public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.ViewHolder> {

    public List<VolActivityInfo> volActivityInfos;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,time,num,info,status;
        Drawable drawable0,drawable1,drawable2,drawable3;
        View serviceView;

        private ViewHolder(View view){
            super(view);
            serviceView=view;
            name=(TextView)view.findViewById(R.id.service_name);
            time=(TextView)view.findViewById(R.id.service_time);
            num=(TextView)view.findViewById(R.id.service_num);
            info=(TextView)view.findViewById(R.id.service_info);
            status=(TextView)view.findViewById(R.id.service_status);
            drawable0=view.getResources().getDrawable(R.drawable.ic_person_black_24dp);
            drawable1=view.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp);
            drawable2=view.getResources().getDrawable(R.drawable.ic_check_black_24dp);
            drawable3=view.getResources().getDrawable(R.drawable.ic_close_black_24dp);

            drawable0.setBounds(0, 0, drawable0.getMinimumWidth(), drawable0.getMinimumHeight());
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());

        }
    }

    public ServiceItemAdapter(List<VolActivityInfo> volActivityInfoList){
        volActivityInfos=volActivityInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_vol_services,parent,false);
        final ViewHolder holder;
        holder=new ViewHolder(view);
        holder.serviceView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                VolActivityInfo item=volActivityInfos.get(holder.getLayoutPosition());
                if(mContext==null)
                    mContext=parent.getContext();
                Intent intent=new Intent(mContext,SignUpActivity.class);
                intent.putExtra("activity_details",(Parcelable) item);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VolActivityInfo activityInfo=volActivityInfos.get(position);
        holder.name.setText(activityInfo.getName());
        holder.time.setText(activityInfo.getRegTime().toString());
        holder.num.setText(Integer.toString(position+1));
        holder.info.setText(activityInfo.getGeneral());
        switch(activityInfo.getStatus()){
            case 0:
                holder.status.setText("未开始");
                holder.status.setCompoundDrawables(holder.drawable0,null,null,null);
                break;
            case 1:
                holder.status.setText("面试中");
                holder.status.setCompoundDrawables(holder.drawable1,null,null,null);
                break;
            case 2:
                holder.status.setText("进行中");
                holder.status.setCompoundDrawables(holder.drawable2,null,null,null);
                break;
            case 3:
                holder.status.setText("已结束");
                holder.status.setCompoundDrawables(holder.drawable3,null,null,null);
                break;
                default:
        }
    }

    @Override
    public int getItemCount() {
        return volActivityInfos.size();
    }
}
