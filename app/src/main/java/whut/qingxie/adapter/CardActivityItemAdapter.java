package whut.qingxie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.SimpleDateFormat;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.activity.SignUpActivity;
import whut.qingxie.common.Content;
import whut.qingxie.entity.activity.VolActivityInfo;

/**
 * 首页卡片式布局适配器
 */
public class CardActivityItemAdapter extends RecyclerView.Adapter<CardActivityItemAdapter.ViewHolder> {
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_HEADER = 0;

    private static View headerView;
    private Context mContext;

    private List<VolActivityInfo> cardActivityItemList;

    public CardActivityItemAdapter(List<VolActivityInfo> cardActivityItems, Context context) {
        cardActivityItemList = cardActivityItems;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(headerView);
        }
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_activity_details, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getRealPosition(holder);
                VolActivityInfo item = cardActivityItemList.get(pos);
                if (mContext == null)
                    mContext = parent.getContext();
                Intent intent = new Intent(mContext, SignUpActivity.class);
                intent.putExtra("activity_details", item.getId());
                mContext.startActivity(intent);
            }
        });
        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/2/10 点击收藏按钮
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        VolActivityInfo cardActivityItem = cardActivityItemList.get(pos);
        holder.title.setText(cardActivityItem.getName());
        holder.info.setText(cardActivityItem.getGeneral());
        if (cardActivityItem.getHomePagePath() == null) {
            Drawable drawable = new ColorDrawable(Color.BLACK);
            holder.title_background.setBackground(drawable);
        } else {
            Glide.with(mContext)
                    .load(Content.getServerHost() + cardActivityItem.getHomePagePath())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            resource = getBlackImage(resource);
                            Drawable drawable = new BitmapDrawable(resource);
                            //设置图片透明度
                            drawable.setAlpha(180);
                            holder.title_background.setBackground(drawable);   //设置背景
                        }
                    });
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String days = sdf.format(cardActivityItem.getCreateTime());
        holder.days.setText(days);
        // TODO: 2018/3/12 收藏按钮变化
            /*收藏按钮变化
        if(cardActivityItem.getFavourite()==true)
            holder.favourite.setCompoundDrawables(img1,null,null,null);
        else
            holder.favourite.setCompoundDrawables(img2,null,null,null);
        */
    }

    @Override
    public int getItemCount() {
        return headerView == null ? cardActivityItemList.size() : cardActivityItemList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    /**
     * 获取加黑遮罩图片
     *
     * @param bm
     * @return
     */
    public Bitmap getBlackImage(Bitmap bm) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.RGB_565);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Canvas canvas = new Canvas(bmp);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bm, 0, 0, paint);
        canvas.drawColor(Color.parseColor("#b0000000"));
        return bmp;

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView title, info, days;
        TextView favourite;
        LinearLayout title_background;

        ViewHolder(View view) {
            super(view);
            if (view == headerView) return;
            cardView = view;
            title = (TextView) view.findViewById(R.id.card_title);
            info = (TextView) view.findViewById(R.id.card_info);
            days = (TextView) view.findViewById(R.id.card_days);
            favourite = (TextView) view.findViewById(R.id.card_favourite);
            title_background = (LinearLayout) view.findViewById(R.id.card_title_background);
        }
    }
}
