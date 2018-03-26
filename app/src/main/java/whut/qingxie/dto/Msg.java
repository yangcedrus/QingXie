package whut.qingxie.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import whut.qingxie.network.JsonUtil;

public class Msg {
    //  状态码
    private String status;

    private String message;

    //用于封装数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public Msg(String status, String message, Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Msg() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Msg parseMapFromJson(Object object, Map<String, String> clazzMap) throws ClassNotFoundException {
        //解析Msg对象
        Msg msgFromJson = JsonUtil.parseObject(object, Msg.class);
        msgFromJson.setData(JsonUtil.parseMap(msgFromJson.getData(), clazzMap));
        return msgFromJson;
    }

    public static Msg parseFromJson(JSONObject object, Map<String, String> clazzMap) throws ClassNotFoundException {
        //解析Msg对象
        Msg msgFromJson = JsonUtil.parseObject(object, Msg.class);
        msgFromJson.setData(JsonUtil.parseMap(msgFromJson.getData(), clazzMap));
        return msgFromJson;
    }
}