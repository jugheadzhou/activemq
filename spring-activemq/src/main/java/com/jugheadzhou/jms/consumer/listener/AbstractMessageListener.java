package com.jugheadzhou.jms.consumer.listener;

import com.jugheadzhou.jms.util.MqMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageUtil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.lang.reflect.ParameterizedType;

/**
 * @description: mq 消息的监听转换处理，采用统一的方式处理，子类直接处理转换后的对象
 * @author: aishu
 * @create: 2019-10-24 12:38
 */
@Slf4j
public abstract class AbstractMessageListener<T> implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String messageText;
        try {
            messageText = ((TextMessage)message).getText();
            log.debug("mq-consumer-get-message : {}",messageText);
            T ret = MqMessageUtil.String2Object(messageText,this.getEntityClass());
            this.execute(ret);
        } catch (JMSException e) {
            log.error(e.toString(),e);
        }
    }

    /**
     * 执行对象的相关逻辑
     * @param message
     * @author zhangxueshu
     */
    public abstract void execute(T message);

    /**
     * 获取子类定义的泛型参数
     * @return
     */
    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass(){
        Class<T> entityClass = null;
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
        return entityClass;
    }
}