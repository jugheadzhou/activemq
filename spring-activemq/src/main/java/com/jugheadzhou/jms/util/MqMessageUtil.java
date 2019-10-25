package com.jugheadzhou.jms.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * @description: mq 消息转换工具
 * @author: aishu
 * @create: 2019-10-24 10:45
 */
public class MqMessageUtil {
    /**
     * 将一个可序列化的对象转换为字符串
     * @param obj
     * @return
     * @author zhangxueshu
     */
    public static String Object2String(Object obj){
        if(obj==null){
            return "";
        }
        String ret = JSONObject.toJSONString(obj);
        return ret;
    }

    /**
     * 将一个字符串反序列化转换为对象
     * @param message
     * @return
     * @author zhangxueshu
     */
    public static <T> T String2Object(String message,Class<T> cls){
        if(StringUtils.isEmpty(message)){
            return null;
        }
        JSONObject json = JSONObject.parseObject(message);
        T obj = JSONObject.toJavaObject(json, cls);
        return obj;
    }
}