package com.jugheadzhou.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @description: spring整合activemq
 * @author: aishu
 * @create: 2019-10-24 09:45
 */
public class QueueProducerTest {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring-config.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) applicationContext.getBean("jmsQueueTemplate");
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("这是spring整合activemq发送的消息");
                return textMessage;
            }
        });
    }
}