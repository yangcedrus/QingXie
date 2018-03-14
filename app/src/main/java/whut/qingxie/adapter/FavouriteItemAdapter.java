package whut.qingxie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.entity.activity.VolActivityInfo;

public class FavouriteItemAdapter extends RecyclerView.Adapter<FavouriteItemAdapter.ViewHolder> {
    public List<VolActivityInfo> volActivityInfos;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,text;

        private ViewHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.favourite_item_title);
            text=(TextView)view.findViewById(R.id.favourite_item_text);
        }
    }

    public FavouriteItemAdapter(List<VolActivityInfo> volActivityInfoList){
        volActivityInfos=volActivityInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite,parent,false);
        ViewHolder holder;
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VolActivityInfo activityInfo=volActivityInfos.get(position);
        holder.title.setText(activityInfo.getName());
        holder.text.setText(activityInfo.getGeneral());
    }

    @Override
    public int getItemCount() {
        return volActivityInfos.size();
    }
}
