package cn.edu.zucc.utils;

import cn.edu.zucc.enums.CommonStatusEnum;
import cn.edu.zucc.enums.DelFlagEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: 基础列的操作
 *
 * @author chenyun
 * @since 03.03.2021
 */
@Slf4j
public class BasicColumnUtils {

    private static final String UPDATE_TIME = "updateTime";
    private static final String UPDATE_ID = "updateId";
    private static final String CREATE_TIME = "createTime";
    private static final String CREATE_ID = "createId";
    private static final String DEL_FLAG = "delFlag";
    private static final String STATUS = "status";

    private BasicColumnUtils() {
    }


    public static List<String> getFieldNames(Object obj) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Class c = obj.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                list.add(f.getName());
            }
        } catch (Exception e) {
            log.debug("[getProperty]", e);
        }
        return list;
    }

    /**
     * 根据字段名获取字段值
     *
     * @param fieldName 字段名
     * @param object    对象
     * @return 字段值
     */
    public static Object getFieldValue(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException e) {
            log.error("[getFieldValue]-NoSuchFieldException:", e);
            return null;
        } catch (IllegalAccessException e) {
            log.error("[getFieldValue]-IllegalAccessException:", e);
            return null;
        }
    }

    /**
     * 根据字段名设置字段值
     *
     * @param object    对象
     * @param fieldName 字段名
     * @param value     字段值
     */
    public static Object setFieldValue(Object object, String fieldName, Object value) {
        Class c = object.getClass();
        try {
            Field f = c.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(object, value);
        } catch (NoSuchFieldException e) {
            log.error("[setFieldValue]-NoSuchFieldException:", e);
        } catch (IllegalAccessException e) {
            log.error("[setFieldValue]-IllegalAccessException:", e);
        }
        return object;
    }

    /**
     * 是否包含字段
     *
     * @param object     object
     * @param fieldNames 字段名
     * @return 是否
     */
    public static boolean containsFields(Object object, String[] fieldNames) {
        Class c = object.getClass();
        try {
            for (String fieldName : fieldNames) {
                c.getDeclaredField(fieldName);
            }
        } catch (NoSuchFieldException e) {
            return false;
        }
        return true;
    }


    public static void setAddUserAndTime(Object object, Date currentDate, Long userId) {
        if (BasicColumnUtils.containsFields(object, new String[]{CREATE_TIME, CREATE_ID})) {
            BasicColumnUtils.setFieldValue(object, CREATE_TIME, currentDate);
            BasicColumnUtils.setFieldValue(object, CREATE_ID, userId);
        }
        if (BasicColumnUtils.containsFields(object, new String[]{UPDATE_TIME, UPDATE_ID})) {
            BasicColumnUtils.setFieldValue(object, UPDATE_TIME, currentDate);
            BasicColumnUtils.setFieldValue(object, UPDATE_ID, userId);
        }
        if (BasicColumnUtils.containsFields(object, new String[]{DEL_FLAG})) {
            BasicColumnUtils.setFieldValue(object, DEL_FLAG, DelFlagEnum.NO.getCode());
        }
        if (BasicColumnUtils.containsFields(object, new String[]{STATUS})) {
            BasicColumnUtils.setFieldValue(object, STATUS, CommonStatusEnum.YES.getCode());
        }
    }


    public static void setUpdateUserAndTime(Object object, Date currentDate, Long userId) {
        if (BasicColumnUtils.containsFields(object, new String[]{UPDATE_TIME, UPDATE_ID})) {
            BasicColumnUtils.setFieldValue(object, UPDATE_TIME, currentDate);
            BasicColumnUtils.setFieldValue(object, UPDATE_ID, userId);
        }
    }

}
