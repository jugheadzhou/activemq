package com.jugheadzhou.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

/**
 * @description: 消息生产者
 * @author: aishu
 * @create: 2019-10-25 09:45
 */
@Service
public class Producer {


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;


    public String sendQueue(String message) {
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        message = uuid + "-" + message;
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
        System.out.println("发送队列消息完成: " + message);
        return "发送队列消息完成: " + message;
    }

    public String sendTopic(String message) {
//        Map<String,String> map = new HashMap(4);
//        map.put("id",UUID.randomUUID().toString().substring(0,6));
//        map.put("message",message);
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        message = uuid + "-" + message;
        this.jmsMessagingTemplate.convertAndSend(this.topic, message);
        System.out.println("发送主题消息完成: " + message);
        return "发送主题消息完成: " + message;
    }


    /**
     * 定时投放消息
     */
    @Scheduled(fixedDelay = 5000)
    public void sendQueueScheduled() {
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String message = uuid + "-" + "TODO定时消息内容...";
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
        System.out.println("发送定时消息完成: " + message);
    }


}