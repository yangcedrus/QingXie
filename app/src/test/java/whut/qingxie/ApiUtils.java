package whut.qingxie;

import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import whut.qingxie.dto.Msg;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * 不能像pure java工程在jvm中运行
 * 因为需要导入android.jar包
 * Created by evans on 2018/3/7.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE,sdk = { 25 })
public class ApiUtils {

    private static final String SERVE_URL="http://123.207.87.34:8080";
    public static String sessionId="";

    /**
     * 同步请求，需开启子线程
     * @param api
     * @param args
     * @return
     */
    public static Msg getDatasync(String api, Object ...args){
        Msg msg=new Msg();
        TempThread<Msg> tempThread=new TempThread<>(msg,Msg.class,api);
        tempThread.start();
        return msg;
    }

    public static void callback(){
        System.out.println("回调结束");
    }

    static class TempThread<T> extends Thread{
        private T t;
        Class<T> clazz;
        String api;
        public TempThread(T t,Class<T> clazz,String api){
            this.t=t;
            this.clazz=clazz;
            this.api=api;
        }
        public void run(){
            try{
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10,TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .build();

                Request request=new Request.Builder()
                        .url(SERVE_URL+api)
                        .addHeader("sessionId",sessionId)
                        .addHeader("Authorization","TOKEN")
                        .build();

                Response response=client.newCall(request).execute();
                if(response.isSuccessful()){
                    String responseData=response.body().string();
                    Gson gson=new Gson();
                    t=gson.fromJson(responseData,clazz);
                    ApiUtils.callback();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Test
    public void accessData() throws Exception {
        Map<String,String> params=new HashMap<>();
        Map<String,String> headers=new HashMap<>();
        System.out.println("nizai哪里呀");

        Msg msg=new Msg();
        OkhttpUtil.accessData("GET", "/user/getAll", params, headers, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("请求失败",e.getMessage());
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Message message=new Message();
                Log.i("response",response);
            }
        });
        System.out.println("hei");
    }
}
