package com.sendtion.xrichtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by sendtion on 2016/6/24.
 * 显示富文本
 */
public class RichTextView extends ScrollView {
    private static final int EDIT_PADDING = 10; // edittext常规padding是10dp

    private int viewTagIndex = 1; // 新生的view都会打一个tag，对每个view来说，这个tag是唯一的。
    private LinearLayout allLayout; // 这个是所有子view的容器，scrollView内部的唯一一个ViewGroup
    private LayoutInflater inflater;
    private int editNormalPadding = 0; //

    public RichTextView(Context context) {
        this(context, null);
    }

    public RichTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);

        // 1. 初始化allLayout
        allLayout = new LinearLayout(context);
        allLayout.setOrientation(LinearLayout.VERTICAL);
        //allLayout.setBackgroundColor(Color.WHITE);//去掉背景
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        allLayout.setPadding(50, 15, 50, 15);//设置间距，防止生成图片时文字太靠边
        addView(allLayout, layoutParams);

        LinearLayout.LayoutParams firstEditParam = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //editNormalPadding = dip2px(EDIT_PADDING);
        TextView firstText = createTextView("没有内容", dip2px(context, EDIT_PADDING));
        allLayout.addView(firstText, firstEditParam);
    }

    public int dip2px(Context context, float dipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }

    /**
     * 清除所有的view
     */
    public void clearAllLayout() {
        allLayout.removeAllViews();
    }

    /**
     * 获得最后一个子view的位置
     *
     * @return
     */
    public int getLastIndex() {
        int lastEditIndex = allLayout.getChildCount();
        return lastEditIndex;
    }

    /**
     * 生成文本输入框
     */
    public TextView createTextView(String hint, int paddingTop) {
        TextView textView = (TextView) inflater.inflate(R.layout.rich_textview, null);
        textView.setTag(viewTagIndex++);
        textView.setPadding(editNormalPadding, paddingTop, editNormalPadding, paddingTop);
        textView.setHint(hint);
        return textView;
    }

    /**
     * 生成图片View
     */
    private RelativeLayout createImageLayout() {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(
                R.layout.edit_imageview, null);
        layout.setTag(viewTagIndex++);
        View closeView = layout.findViewById(R.id.image_close);
        closeView.setVisibility(GONE);
        return layout;
    }

    /**
     * 在特定位置插入EditText
     *
     * @param index   位置
     * @param editStr EditText显示的文字
     */
    public void addTextViewAtIndex(final int index, CharSequence editStr) {
        TextView textView = createTextView("", EDIT_PADDING);
        textView.setText(editStr);

        allLayout.addView(textView, index);
    }

    /**
     * 在特定位置添加ImageView
     */
    public void addImageViewAtIndex(final int index, String imagePath) {
        final RelativeLayout imageLayout = createImageLayout();
        DataImageView imageView = (DataImageView) imageLayout.findViewById(R.id.edit_imageView);
        RequestOptions myOptions = new RequestOptions().centerCrop();
        Glide.with(getContext())
                .load(imagePath)
                .transition(withCrossFade())
                .apply(myOptions)
                .into(imageView);
        //imageView.setImageBitmap(bmp);//这里改用Glide加载图片
        //imageView.setBitmap(bmp);//这句去掉，保留下面的图片地址即可，优化图片占用
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//裁剪剧中
        imageView.setAbsolutePath(imagePath);

        // 调整imageView的高度
        BitmapFactory.Options opt = new BitmapFactory.Options();
        // 这个isjustdecodebounds很重要
        opt.inJustDecodeBounds = true;//只解析边缘，有效防止oom

        // TODO: 2018/5/17 冗余网络操作，可替换为Glide得到宽高 
        HttpURLConnection conn = null;
        try {
            URL myUri = new URL(imagePath); // 创建URL对象
            // 创建链接
            conn = (HttpURLConnection) myUri.openConnection();
            conn.setConnectTimeout(10000);// 设置链接超时
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");// 设置请求方法为get
            conn.connect();// 开始连接
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                // 根据流数据创建 一个Bitmap位图对象
                Bitmap bmp = BitmapFactory.decodeStream(is,(Rect) null,opt);
                int imageHeight = 500;
                try {
                    imageHeight = allLayout.getWidth() * opt.outHeight / opt.outWidth;
                    bmp.recycle();
                    bmp = null;
                    System.gc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // TODO: 17/3/1 调整图片高度，这里是否有必要，如果出现微博长图，可能会很难看
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, imageHeight);//设置图片固定高度
                lp.bottomMargin = 10;
                imageView.setLayoutParams(lp);
                // 访问成功
            } else {
                Log.i(TAG, "访问失败：responseCode=" + responseCode);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();

            }
        }
//        Bitmap bmp = BitmapFactory.decodeFile(imagePath,opt);
//        int imageHeight = 500;
//        try {
//            imageHeight = allLayout.getWidth() * opt.outHeight / opt.outWidth;
//            bmp.recycle();
//            bmp = null;
//            System.gc();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // TODO: 17/3/1 调整图片高度，这里是否有必要，如果出现微博长图，可能会很难看
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                LayoutParams.MATCH_PARENT, imageHeight);//设置图片固定高度
//        lp.bottomMargin = 10;
//        imageView.setLayoutParams(lp);

        allLayout.addView(imageLayout, index);
    }

    /**
     * 根据view的宽度，动态缩放bitmap尺寸
     *
     * @param width view的宽度
     */
    public Bitmap getScaledBitmap(String filePath, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int sampleSize = options.outWidth > width ? options.outWidth / width
                + 1 : 1;
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

}
