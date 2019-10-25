package com.jugheadzhou.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @description:
 * @author: aishu
 * @create: 2019-10-24 13:39
 */
public class QueueConsumerTest {
    public static void main(String[] args) throws Exception{

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring-config.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) applicationContext.getBean("jmsQueueTemplate");
        TextMessage message = (TextMessage)jmsTemplate.receive("scoreQueueDestination");
        System.out.println("接收到消息："+message.getText());
    }
}