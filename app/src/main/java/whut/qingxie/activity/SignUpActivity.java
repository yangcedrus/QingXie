package whut.qingxie.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sendtion.xrichtext.RichTextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.helper.ImageUtils;
import whut.qingxie.helper.ScreenUtils;
import whut.qingxie.helper.StringUtils;

public class SignUpActivity extends AppCompatActivity {
    private static RichTextView et_new_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sign_up);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final VolActivityInfo activityInfo=getIntent().getParcelableExtra("activity_details");

        //加载布局
        TextView title=(TextView)findViewById(R.id.sign_up_info);
        et_new_content=(RichTextView) findViewById(R.id.sign_up_description);
        TextView people_have=(TextView)findViewById(R.id.textView_people_have);
        TextView people_needed=(TextView)findViewById(R.id.textView_people_needed);

        title.setText(activityInfo.getName());
        et_new_content.post(new Runnable() {
            @Override
            public void run() {
                showTextData(activityInfo.getDescriptions());
            }
        });
        people_have.setText(Integer.toString(activityInfo.getNeedVolunteers()));
        people_needed.setText(Integer.toString(activityInfo.getNeedVolunteers()));

        Button button_sign_up=(Button)findViewById(R.id.sign_up_confirm);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this, RichTextEditorActivity.class);
                startActivity(intent);
            }
        });
    }

    //显示“反馈”菜单按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //设置“反馈”菜单按钮响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(SignUpActivity.this, HelpInfoActivity.class);
                startActivity(intent);
        }
        return true;
    }

    private void showTextData(String content) {
        et_new_content.clearAllLayout();
        if(content==null){
            // TODO: 2018/3/24 详情为空的处理 
            return;
        }
        List<String> textList = StringUtils.cutStringByImgTag(content);
        for (int i = 0; i < textList.size(); i++) {
            String text = textList.get(i);
            if (text.contains("<img")) {
                final String imagePath = StringUtils.getImgSrc(text);
                int width = ScreenUtils.getScreenWidth(this);
                int height = ScreenUtils.getScreenHeight(this);
                et_new_content.measure(0,0);
                if(text.contains("http")){
                    et_new_content.addImageViewAtIndex(et_new_content.getLastIndex(),imagePath);
                }else{
                    //文件读取图片
                    //待删除
                    Bitmap bitmap = ImageUtils.getSmallBitmap(imagePath, width, height);
                    if (bitmap != null){
                        et_new_content.addImageViewAtIndex(et_new_content.getLastIndex(),imagePath);
                    } else {
                        et_new_content.addTextViewAtIndex(et_new_content.getLastIndex(), text);
                    }
                }
            }else{
                et_new_content.addTextViewAtIndex(et_new_content.getLastIndex(), text);
            }
        }
    }
}
