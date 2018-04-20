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

import com.google.gson.Gson;
import com.sendtion.xrichtext.RichTextEditor;
import com.sendtion.xrichtext.SDCardUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.iwf.photopicker.PhotoPicker;
import okhttp3.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;

import static android.content.ContentValues.TAG;

/**
 * 富文本编写类
 * getEditData可以获得字符串并做相应处理
 * WorkerWorkFragment第一个item
 */
public class RichTextEditorActivity extends AppCompatActivity {

    private RichTextEditor et_new_content;
    private EditText et_new_title;

    private List<String> imagePathList=new ArrayList<>();

    private Subscription subsLoading;
    private Subscription subsInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text_editor);

        et_new_title=(EditText)findViewById(R.id.et_new_title);
        et_new_content=(RichTextEditor)findViewById(R.id.et_new_content);

        Intent intent=getIntent();
        et_new_title.setText(intent.getStringExtra("release_work_title"));

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
            case R.id.action_insert_image:{
                //第三方加载
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(false)
                        .setPreviewEnabled(false)
                        .start(this);
                break;
            }
            case android.R.id.home:{
                showDialog();
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
                        Log.d("photoPath:", s);
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
                    for (String imagePath : photos) {
                        //压缩图片
                        upload_image(imagePath);
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
                        et_new_content.addEditTextAtIndex(et_new_content.getLastIndex(), "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String imagePath) {
                        et_new_content.insertImage(imagePath, et_new_content.getMeasuredWidth());
                    }
                });
    }

    /**
     * 获取richeditor中的字符串
     * @return richeditor中的字符串
     */
    private String getEditData() {
        int flag=0;
        List<RichTextEditor.EditData> editList = et_new_content.buildEditData();
        StringBuffer content = new StringBuffer();
        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                content.append(itemData.inputStr);
            } else if (itemData.imagePath != null) {
                content.append("<img src=\"").append(Content.getServerHost()+imagePathList.get(flag++)).append("\"/>");
            }
        }
        return content.toString();
    }

    /**
     * 虚拟键盘返回按钮点击事件响应
     */
    @Override
    public void onBackPressed() {
        showDialog();
    }

    /**
     * 设置退出时对话框
     */
    private void showDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_exit_black_24dp)//设置标题的图片
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

    /**
     * 上传图片
     * @param imagePath
     */
    private void upload_image(String imagePath) {
        File file = new File(imagePath);

        if (!file.exists())
            return;
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("content-type", "multipart/form-data; boundary=" + UUID.randomUUID().toString());
        headerMap.put("connection", "keep-alive");
        headerMap.put("user-agent", "android");

        //FIXME 原本Okhttputil无法完成
        OkHttpUtils.post()//
                .addFile("pic", file.getName(), file)
                .url(Content.getServerHost() + "/activity/" + Content.getUserId() + "/pic/add")
                .headers(headerMap)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RichTextEditorActivity.this, "连接超时，请检查网络连接", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Msg msg = gson.fromJson(response, Msg.class);
                        if (msg.getStatus().equals("error")) {
                            Toast.makeText(RichTextEditorActivity.this, "图片上传失败", Toast.LENGTH_LONG).show();
                        } else {
                            String image = (String) msg.getData().get("picAccessPath");
                            imagePathList.add(image);
                        }
                    }
                });
    }
}
