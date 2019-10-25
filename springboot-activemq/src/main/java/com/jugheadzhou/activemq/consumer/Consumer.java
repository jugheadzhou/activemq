package com.jugheadzhou.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 消费者
 * @author: aishu
 * @create: 2019-10-25 09:45
 */
@Component
public class Consumer {


    @JmsListener(destination = "springboot-queue01")
    public void listenQueue(String msg) {
        //模拟业务执行时长
        try {
//            System.out.println("开始等待1s..." + new Date());
            Thread.sleep(1000);
//            System.out.println("等待结束..." + new Date());
        } catch (Exception e) {
            System.exit(0);//退出程序
        }
        System.out.println("******执行业务逻辑...******");
        System.out.println("接收到队列消息：" + msg);
    }

    @JmsListener(destination = "springboot-topic01", containerFactory = "jmsTopicListenerContainerFactory")
    public void listenTopic(String msg) {
        //模拟业务执行时长
        try {
            System.out.println("开始等待2s..." + new Date());
            Thread.sleep(2000);
            System.out.println("等待结束..." + new Date());
        } catch (Exception e) {
            System.exit(0);//退出程序
        }
        System.out.println("接收到主题消息：" + msg);
    }
}