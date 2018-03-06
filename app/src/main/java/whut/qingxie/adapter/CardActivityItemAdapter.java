package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.CardActivityItem;
import whut.qingxie.activity.SignUpActivity;

public class CardActivityItemAdapter extends RecyclerView.Adapter<CardActivityItemAdapter.ViewHolder> {
    private Context mContext;

    private List<CardActivityItem> cardActivityItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View cardView;
        TextView title,info,days;
        TextView favourite,details,signup;

        ViewHolder(View view){
            super(view);
            cardView=view;
            title=(TextView)view.findViewById(R.id.card_title);
            info=(TextView)view.findViewById(R.id.card_info);
            days=(TextView)view.findViewById(R.id.card_days);
            favourite=(TextView)view.findViewById(R.id.card_favourite);
            details=(TextView)view.findViewById(R.id.card_details);
            signup=(TextView)view.findViewById(R.id.card_sign_up);
        }
    }

    public CardActivityItemAdapter(List<CardActivityItem> cardActivityItems){
        cardActivityItemList=cardActivityItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_activity_details,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                CardActivityItem item = cardActivityItemList.get(pos);
                if(mContext==null)
                    mContext=parent.getContext();
                Intent intent=new Intent(mContext,SignUpActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.favourite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: 2018/2/10 点击收藏按钮
                /*待实现
                int pos = holder.getAdapterPosition();
                CardActivityItem item = cardActivityItemList.get(pos);
                if(mContext==null)
                    mContext=parent.getContext();
                Drawable img1 = mContext.getDrawable(R.drawable.ic_favorite_black_24dp);
                Drawable img2 = mContext.getDrawable(R.drawable.ic_favorite_border_black_24dp);
                //必须添加否则图片不显示
                img1.setBounds(0, 0, img1.getMinimumWidth(), img1.getMinimumHeight());
                img2.setBounds(0, 0, img2.getMinimumWidth(), img2.getMinimumHeight());
                if(item.getFavourite()==true){
                    // TODO: 2018/2/11 取消收藏 
                    holder.favourite.setCompoundDrawables(img2,null,null,null);
                }else if(item.getFavourite()==false){
                    // TODO: 2018/2/11 添加收藏
                    holder.favourite.setCompoundDrawables(img1,null,null,null);
                }*/
            }
        });
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardView.callOnClick();
            }
        });
        holder.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/2/10 点击报名
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardActivityItem cardActivityItem = cardActivityItemList.get(position);
        holder.title.setText(cardActivityItem.getTitle());
        holder.info.setText(cardActivityItem.getInfo());
        String s=cardActivityItem.getDays()+"天";
        /*收藏按钮变化
        if(cardActivityItem.getFavourite()==true)
            holder.favourite.setCompoundDrawables(img1,null,null,null);
        else
            holder.favourite.setCompoundDrawables(img2,null,null,null);
        */
        holder.days.setText(s);
    }

    @Override
    public int getItemCount() {
        return cardActivityItemList.size();
    }
}
