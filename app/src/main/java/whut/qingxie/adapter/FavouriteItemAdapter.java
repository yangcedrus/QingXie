package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;

/**
 * 学生收藏页面item适配器
 */
public class FavouriteItemAdapter extends RecyclerView.Adapter<FavouriteItemAdapter.ViewHolder> {
    public List<Activity4User> activity4Users;

    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,text;
        View favouriteView;

        private ViewHolder(View view){
            super(view);
            favouriteView=view;
            title=(TextView)view.findViewById(R.id.favourite_item_title);
            text=(TextView)view.findViewById(R.id.favourite_item_text);
        }
    }

    public FavouriteItemAdapter(List<Activity4User> activity4Users){
        this.activity4Users=activity4Users;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite,parent,false);
        final ViewHolder holder;
        holder=new ViewHolder(view);
        holder.favouriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity4User item=activity4Users.get(holder.getLayoutPosition());
                if(mContext==null)
                    mContext=parent.getContext();
                Intent intent=new Intent(mContext,SignUpActivity.class);
                intent.putExtra("activity_details",item.getActivityId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity4User activityInfo=activity4Users.get(position);
        holder.title.setText(activityInfo.getName());
        holder.text.setText(activityInfo.getProfile());
    }

    @Override
    public int getItemCount() {
        return activity4Users.size();
    }
}
