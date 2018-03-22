package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.entity.activity.VolActivityInfo;

public class CardActivityItemAdapter extends RecyclerView.Adapter<CardActivityItemAdapter.ViewHolder> {
    private static final int TYPE_NORMAL=1;
    private static final int TYPE_HEADER=0;

    private static View headerView;
    private Context mContext;

    private List<VolActivityInfo> cardActivityItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View cardView;
        TextView title,info,days;
        TextView favourite,details,signup;

        ViewHolder(View view){
            super(view);
            if(view==headerView)    return;
            cardView=view;
            title=(TextView)view.findViewById(R.id.card_title);
            info=(TextView)view.findViewById(R.id.card_info);
            days=(TextView)view.findViewById(R.id.card_days);
            favourite=(TextView)view.findViewById(R.id.card_favourite);
            details=(TextView)view.findViewById(R.id.card_details);
            signup=(TextView)view.findViewById(R.id.card_sign_up);
        }
    }

    public CardActivityItemAdapter(List<VolActivityInfo> cardActivityItems){
        cardActivityItemList=cardActivityItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if(headerView!=null&&viewType==TYPE_HEADER) {
            return new ViewHolder(headerView);
        }
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_activity_details,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int pos = getRealPosition(holder);
                VolActivityInfo item = cardActivityItemList.get(pos);
                if(mContext==null)
                    mContext=parent.getContext();
                Intent intent=new Intent(mContext,SignUpActivity.class);
                intent.putExtra("activity_details",(Parcelable)item);
                mContext.startActivity(intent);
            }
        });
        holder.favourite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: 2018/2/10 点击收藏按钮
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
        if (getItemViewType(position)==TYPE_HEADER) return;

        final int pos=getRealPosition(holder);
        VolActivityInfo cardActivityItem = cardActivityItemList.get(pos);
        holder.title.setText(cardActivityItem.getName());
        holder.info.setText(cardActivityItem.getGeneral());

        long days=0;  //天数

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1=new Date();
            Date date2=sdf.parse(cardActivityItem.getRegTime().toString());

            long diff=date2.getTime()-date1.getTime();
            days = diff / (1000 * 60 * 60 * 24);
        }catch (ParseException e){
            e.printStackTrace();
            // TODO: 2018/3/7 解析异常捕获处理
        }
        String s=days+"天";

        // TODO: 2018/3/12 收藏按钮变化 
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
        return headerView == null ? cardActivityItemList.size() : cardActivityItemList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(headerView==null)    return TYPE_NORMAL;
        if(position==0)  return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder){
        int position=holder.getLayoutPosition();
        return headerView==null? position:position-1;
    }

    public View getHeaderView(){
        return headerView;
    }

    public void setHeaderView(View headerView){
        this.headerView=headerView;
        notifyItemInserted(0);
    }
}
