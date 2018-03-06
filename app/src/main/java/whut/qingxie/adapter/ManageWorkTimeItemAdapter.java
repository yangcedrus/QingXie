package whut.qingxie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import whut.qingxie.R;
import whut.qingxie.Item.ManageWorkTimeItem;

public class ManageWorkTimeItemAdapter extends RecyclerView.Adapter<ManageWorkTimeItemAdapter.ViewHolder>{
    private List<ManageWorkTimeItem> manageWorkTimeItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name,title,detail;
        ImageView IMAGE_OK,IMAGE_CANCEL;

        public ViewHolder(View view) {
            super(view);
            image=(CircleImageView)view.findViewById(R.id.worktime_icon);
            name=(TextView)view.findViewById(R.id.worktime_name);
            title=(TextView)view.findViewById(R.id.worktime_title);
            detail=(TextView)view.findViewById(R.id.worktime_detail);
            IMAGE_OK=(ImageView)view.findViewById(R.id.worktime_ok);
            IMAGE_CANCEL=(ImageView)view.findViewById(R.id.worktime_cancel);
        }
    }

    public ManageWorkTimeItemAdapter(List<ManageWorkTimeItem> manageWorkTimeItemList){
        this.manageWorkTimeItemList=manageWorkTimeItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_worktime_item,parent,false);
        final ViewHolder viewHolder;
        viewHolder = new ViewHolder(view);
        viewHolder.IMAGE_OK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/3 弹出确认对话框
            }
        });
        viewHolder.IMAGE_CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/3 弹出确认对话框
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ManageWorkTimeItem manageWorkTimeItem=manageWorkTimeItemList.get(position);
        holder.image.setImageResource(manageWorkTimeItem.getImage());
        holder.name.setText(manageWorkTimeItem.getName());
        holder.title.setText(manageWorkTimeItem.getTitle());
        holder.detail.setText(manageWorkTimeItem.getDetail());
    }

    @Override
    public int getItemCount() {
        return manageWorkTimeItemList.size();
    }
}
