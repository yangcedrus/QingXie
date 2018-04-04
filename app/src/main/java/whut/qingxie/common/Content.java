package whut.qingxie.common;

import java.util.HashMap;
import java.util.Map;

import whut.qingxie.entity.user.UserExperience;


/**
 * 存放所有公共资源
 * Created by evans on 2018/3/22.
 */

public class Content {
    /**
     * 主机名
     */
    public static final String SERVER_HOST = "http://123.207.87.34:8080/";
    /**
     * 映射类的全路径名
     */
    public static final Map<String, String> CLAZZ_MAP = new HashMap<>();

    static {
        CLAZZ_MAP.put("Activity", "whut.qingxie.entity.activity.VolActivityInfo");
        CLAZZ_MAP.put("User", "whut.qingxie.entity.user.User");
        CLAZZ_MAP.put("Resume", "whut.qingxie.entity.user.Resume");
        CLAZZ_MAP.put("Experience", "whut.qingxie.entity.user.UserExperience");
        CLAZZ_MAP.put("UserActivity","whut.qingxie.entity.activity.Activity4User");
    }
}
