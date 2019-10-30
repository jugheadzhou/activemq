package com.jugheadzhou.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @description:
 * @author: aishu
 * @create: 2019-10-24 13:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-config.xml")
public class QueueConsumerTest {
    @Autowired
    @Qualifier(value = "jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    @Test
    public void receive() throws Exception{
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring-config.xml");
//
//        JmsTemplate jmsTemplate = (JmsTemplate) applicationContext.getBean("jmsQueueTemplate");
        TextMessage message = (TextMessage)jmsTemplate.receive("scoreQueueDestination");
        System.out.println("接收到消息："+message.getText());
    }
}