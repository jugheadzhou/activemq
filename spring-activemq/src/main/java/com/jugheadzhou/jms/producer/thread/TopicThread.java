package com.jugheadzhou.jms.producer.thread;

import com.jugheadzhou.jms.util.DateUtil;
import com.jugheadzhou.jms.util.MqMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @description: 发送主题消息的总线
 * @author: aishu
 * @create: 2019-10-24 10:50
 */
@Slf4j
public class TopicThread implements Runnable {
    private JmsTemplate jmsTemplate;
    private String destination;
    private Object message;

    public TopicThread(JmsTemplate jmsTemplate, String destination, Object message) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
        this.message = message;
    }

    @Override
    public void run() {
        log.info("开始发送消息,目的地是{}",destination);
        synchronized (jmsTemplate){
            jmsTemplate.setTimeToLive(DateUtil.getTomorrowDate().getTime() - System.currentTimeMillis() - 1);
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    String msg = MqMessageUtil.Object2String(message);
                    log.info("mq-producer-send-message: {}",msg);
                    return session.createTextMessage(msg);
                }
            });
        }


    }

}