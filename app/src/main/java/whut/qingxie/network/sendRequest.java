package whut.qingxie.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import whut.qingxie.bean.UserInfo;
import whut.qingxie.bean.VolActivityInfo;
import whut.qingxie.network.parseJSON;

public class sendRequest {

    private static String SERVE_URL="http://123.207.87.34:8080/";
    private static String GET_ALL_ACTIVITIES="activity/getAllActivities";

    //请求一个活动信息
    public static VolActivityInfo requestForOneActivity(){
        VolActivityInfo activityInfos=new VolActivityInfo();

        return  activityInfos;
    }

    //请求五个活动信息
    public static List<VolActivityInfo> requestForFiveActivity(){
        List<VolActivityInfo> activityInfos=new ArrayList<>();

        HttpUtil.sendOkHttpRequest(SERVE_URL + GET_ALL_ACTIVITIES, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();


            }
        });


/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10,TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .build();

                    Request request=new Request.Builder()
                            .url(SERVE_URL+GET_ALL_ACTIVITIES)
                            .build();

                    Response response=client.newCall(request).execute();
                    if(response.isSuccessful()){
                        String responseData=response.body().string();

                        parseJSON.parseActivity(responseData);
                    }
                }catch (Exception e){
                    // TODO: 2018/3/6 异常捕获处理 
                    e.printStackTrace();
                }
            }
        }).start();
*/
        return null;
    }

    //请求个人信息
    public static UserInfo requestForUser(){
        UserInfo user=new UserInfo();

        return user;
    }

    //请求首页图片地址
    public static List<String> requestForHomePictures(){
        List<String> urls=new ArrayList<>();

        return urls;
    }

    //发送新发布活动信息,返回操作结果
    public static String sendNewActivity(VolActivityInfo activityInfo){

        return null;
    }

    //发送个人信息修改，返回操作结果
    public static String sendUpdateUserInfo(UserInfo user){
        // TODO: 2018/3/5 传递修改后的个人信息 
        return null;
    }

    //发送报名信息,返回报名结果
    public static boolean requestForSignUp(UserInfo user, VolActivityInfo activityInfo){
        // TODO: 2018/3/5 仅传递ID是否可行 
        return false;
    }
}
