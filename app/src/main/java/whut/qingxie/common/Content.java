package whut.qingxie.common;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;


/**
 * 存放所有公共资源
 */
public class Content extends Application {
    /**
     * 主机名
     */
    private static final String SERVER_HOST = "http://123.207.87.34:8080";

    /**
     * 映射类的全路径名
     */
    private static final Map<String, String> CLAZZ_MAP = new HashMap<>();
    static {
        CLAZZ_MAP.put("Activity", "whut.qingxie.entity.activity.VolActivityInfo");
        CLAZZ_MAP.put("User", "whut.qingxie.entity.user.User");
        CLAZZ_MAP.put("Resume", "whut.qingxie.entity.user.Resume");
        CLAZZ_MAP.put("Experience", "whut.qingxie.entity.user.UserExperience");
        CLAZZ_MAP.put("UserActivity", "whut.qingxie.entity.activity.Activity4User");
        CLAZZ_MAP.put("HomePagePicture", "whut.qingxie.entity.activity.HomepagePicture");
        CLAZZ_MAP.put("MyHours", "whut.qingxie.Item.MyHoursItem");
        CLAZZ_MAP.put("UserSign", "whut.qingxie.entity.user.UserSign");
    }

    /**
     * -1为未登录
     */
    private static Integer USER_ID = -1;

    /**
     * 学号
     */
    private static String STUDENT_ID = null;

    private static String NAME = null;

    /**
     * -1为未登录,默认
     * 0为学生
     * 1为工作人员
     * 2为管理员
     */
    private static int FLAG = -1;

    /**
     * M为男，默认
     * F为女
     */
    private static String GENDER = "M";

    /**
     * 头像地址
     */
    private static String iconAccessPath=null;

    public static int getFLAG() {
        return FLAG;
    }

    public static void setFLAG(int FLAG) {
        Content.FLAG = FLAG;
    }

    public static String getGENDER() {
        return GENDER;
    }

    public static void setGENDER(String GENDER) {
        Content.GENDER = GENDER;
    }

    public static String getNAME() {
        return NAME;
    }

    public static void setNAME(String NAME) {
        Content.NAME = NAME;
    }

    public static String getServerHost() {
        return SERVER_HOST;
    }

    public static Map<String, String> getClazzMap() {
        return CLAZZ_MAP;
    }

    public static Integer getUserId() {
        return USER_ID;
    }

    public static void setUserId(Integer userId) {
        USER_ID = userId;
    }

    public static String getIconAccessPath() {
        return iconAccessPath;
    }

    public static void setIconAccessPath(String iconAccessPath) {
        Content.iconAccessPath = iconAccessPath;
    }

    public static String getStudentId() {
        return STUDENT_ID;
    }

    public static void setStudentId(String studentId) {
        STUDENT_ID = studentId;
    }
}

//public class Content {
//    /**
//     * 主机名
//     */
//    public static final String SERVER_HOST = "http://123.207.87.34:8080/";
//    /**
//     * 映射类的全路径名
//     */
//    public static final Map<String, String> CLAZZ_MAP = new HashMap<>();
//
//    static {
//        CLAZZ_MAP.put("Activity", "whut.qingxie.entity.activity.VolActivityInfo");
//        CLAZZ_MAP.put("User", "whut.qingxie.entity.user.User");
//        CLAZZ_MAP.put("Resume", "whut.qingxie.entity.user.Resume");
//        CLAZZ_MAP.put("Experience", "whut.qingxie.entity.user.UserExperience");
//        CLAZZ_MAP.put("UserActivity","whut.qingxie.entity.activity.Activity4User");
//    }
//}
