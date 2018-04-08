package whut.qingxie.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.sendtion.xrichtext.RichTextEditor;
import com.sendtion.xrichtext.SDCardUtil;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import whut.qingxie.R;

/**
 * 富文本编写类
 * getEditData可以获得字符串并做相应处理
 * WorkerWorkFragment第一个item
 */
public class RichTextEditorActivity extends AppCompatActivity {
    private Boolean isSaved=false;
    private String rich_text;

    private RichTextEditor et_new_content;
    private EditText et_new_title;

    private ProgressDialog loadingDialog;
    private ProgressDialog insertDialog;

    private Subscription subsLoading;
    private Subscription subsInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text_editor);

        et_new_title=(EditText)findViewById(R.id.et_new_title);
        et_new_content=(RichTextEditor)findViewById(R.id.et_new_content);

        insertDialog = new ProgressDialog(this);
        insertDialog.setMessage("正在插入图片...");
        insertDialog.setCanceledOnTouchOutside(false);

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("图片解析中...");
        loadingDialog.setCanceledOnTouchOutside(false);

        //显示返回按钮
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_rich_edit_text);
        toolbar.setTitle("编辑");
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * 特殊按钮显示
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_toolbar,menu);
        return true;
    }

    /**
     * 特殊按钮点击响应
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new_save:{
                rich_text=getEditData();
                isSaved=true;
                Toast.makeText(RichTextEditorActivity.this, "点击了保存按钮", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action_insert_image:{
                //第三方加载
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(false)
                        .setPreviewEnabled(false)
                        .start(this);
                break;
            }
            case android.R.id.home:{
                Intent intent=new Intent();
                if(isSaved){
                    intent.putExtra("rich_text_return",rich_text);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    showDialog();
                }
                break;
            }
        }
        return true;
    }

    /**
     * 获得返回的路径
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == PhotoPicker.REQUEST_CODE){
                    //异步方式插入图片
                    insertImagesSync(data);

                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    for(String s:photos){
                        Log.d("1111111111111111111:", s);
                    }
                }
            }
        }
    }

    /**
     * 异步方式插入图片
     * @param data
     */
    private void insertImagesSync(final Intent data){

        subsInsert = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try{
                    et_new_content.measure(0, 0);
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        //压缩图片
                        subscriber.onNext(imagePath);
                    }
                    subscriber.onCompleted();
                }catch (Exception e){
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        })
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        insertDialog.dismiss();
                        et_new_content.addEditTextAtIndex(et_new_content.getLastIndex(), "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        insertDialog.dismiss();
                    }

                    @Override
                    public void onNext(String imagePath) {
                        et_new_content.insertImage(imagePath, et_new_content.getMeasuredWidth());
                    }
                });
    }

    /**
     * 获取richeditor中的字符串
     * @return
     */
    private String getEditData() {
        List<RichTextEditor.EditData> editList = et_new_content.buildEditData();
        StringBuffer content = new StringBuffer();
        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                content.append(itemData.inputStr);
            } else if (itemData.imagePath != null) {
                content.append("<img src=\"").append(itemData.imagePath).append("\"/>");
            }
        }
        return content.toString();
    }

    /**
     * 虚拟键盘返回按钮点击事件响应
     */
    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        if(isSaved){
            intent.putExtra("rich_text_return",rich_text);
            setResult(RESULT_OK,intent);
            finish();
        }else {
            showDialog();
        }
    }

    /**
     * 设置退出时对话框
     */
    private void showDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_home_black_24dp)//设置标题的图片
                .setTitle("退出")//设置对话框的标题
                .setMessage("是否要保存内容")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RichTextEditorActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RichTextEditorActivity.this, "点击了保存按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent intent=new Intent();
                        intent.putExtra("rich_text_return",getEditData());
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }).create();
        dialog.show();
    }
}
