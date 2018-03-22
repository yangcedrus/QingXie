package whut.qingxie.network;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author evans 2018/3/19 17:01
 */

public class JsonUtil {
    /**
     * 解析单个对象
     *
     * @param jsonObject
     * @return
     */
    public final static <T> T parseObject(Object jsonObject, Class<T> clazz) {
        return JSON.parseObject(jsonObject.toString(), clazz);
    }

    /**
     * 解析一个List
     *
     * @param <T>
     * @return
     */
    public static <T extends List, E> T parseList(Object jsonObject, Class<T> listType, Class<E> contentClazz) {
        T list = JSON.parseObject(jsonObject.toString(), listType);
        T result;
        try {
            result = listType.newInstance();
            for (Object obj : list) {
                E e = JSON.parseObject(obj.toString(), contentClazz);
                result.add(e);
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析一个List
     *
     * @param <E>
     * @param clazz E 的class对象
     * @return
     */
    public static <E> List<E> parseList(Object jsonObject, Class<E> clazz) {
        List list = JSON.parseObject(jsonObject.toString(), List.class);
        List<E> result;
        try {
            result = new ArrayList<>();
            for (Object obj : list) {
                E e = parseObject(obj, clazz);
                result.add(e);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析map的方法
     *
     * @param jsonMap  包含json对象的
     * @param clazzMap key 为类名，value为类的全路径名
     * @return
     */
    public static Map parseMap(Map<String, Object> jsonMap, Map<String, String> clazzMap) throws ClassNotFoundException {
        //遍历jsonMap
        //如果value是单个对象，则通过parseObject解析
        //如果value是个List，则通过parseList解析
        //区分value，通过key是否以List结尾
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> obj : jsonMap.entrySet()) {
            if (obj.getKey().endsWith("List")) {
                String className = obj.getKey().replace("List", "");
                Class clazz = Class.forName(clazzMap.get(className));
                result.put(obj.getKey(), parseList(obj.getValue(), clazz));
            } else if (clazzMap.containsKey(obj.getKey())) {
                //需统一规范
                Class clazz = Class.forName(clazzMap.get(obj.getKey()));
                result.put(obj.getKey(), parseObject(obj.getValue(), clazz));
            }else{
                //如果不是List且clazzMap中不含对应的类，则当作字符串处理（不需作解析）,但这在解析Map对象的时候就完成解析了~~
                result.put(obj.getKey(), obj.getValue());
            }
        }
        return result;
    }

}
