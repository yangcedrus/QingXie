package whut.qingxie.dto;

import java.util.HashMap;
import java.util.Map;

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
}