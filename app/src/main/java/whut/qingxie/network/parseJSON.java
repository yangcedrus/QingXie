package whut.qingxie.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.dto.Msg;
import whut.qingxie.entity.user.UserInfo;
import whut.qingxie.entity.activity.VolActivityInfo;

public class parseJSON {

    private static String ACTIVITY_KEY="activities";
    private static String USERS_KEY="uesrs";
    private static String HOME_PICTURES_URLS_KEY="home_pictures_urls";

    //处理活动JSON
    public static List<VolActivityInfo> parseActivity(String jsonData){
        List<VolActivityInfo> a=new ArrayList<>();
        try{
            Gson gson=new Gson();
            Msg msg =gson.fromJson(jsonData,Msg.class);

            //得到实例
            String jsonActivates=gson.toJson(msg.getData().get(ACTIVITY_KEY));
            return gson.fromJson(jsonActivates,new TypeToken<List<VolActivityInfo>>(){}.getType());
        }catch (Exception e){
            // TODO: 2018/3/5 捕获处理
            e.printStackTrace();
            return null;
        }
    }

    //处理个人信息JSON
    public static List<UserInfo> parseUser(String jsonData){
        try{
            Gson gson=new Gson();
            Msg msg =gson.fromJson(jsonData,Msg.class);

            //得到实例
            String jsonUsers=gson.toJson(msg.getData().get(USERS_KEY));
            return gson.fromJson(jsonUsers,new TypeToken<List<UserInfo>>(){}.getType());
        }catch (Exception e){
            // TODO: 2018/3/5 捕获处理
            e.printStackTrace();
            return null;
        }
    }

    //处理首页图片url
    public static List<String> parseURL(String jsonData){
        try{
            Gson gson=new Gson();
            Msg msg =gson.fromJson(jsonData,Msg.class);

            //得到实例
            String jsonUsrls=gson.toJson(msg.getData().get(HOME_PICTURES_URLS_KEY));
            return gson.fromJson(jsonUsrls,new TypeToken<List<String>>(){}.getType());
        }catch (Exception e){
            // TODO: 2018/3/5 捕获处理
            e.printStackTrace();
            return null;
        }
    }
}
