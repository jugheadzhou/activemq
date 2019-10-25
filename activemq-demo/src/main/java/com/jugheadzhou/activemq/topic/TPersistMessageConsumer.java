package com.jugheadzhou.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * @description: 主题模式 持久化消息消费者
 * @author: aishu
 * @create: 2019-10-21 11:42
 */
public class TPersistMessageConsumer {
    //默认值是 "failover://tcp://localhost:61616"
    private static final String brokerURL = "failover://tcp://localhost:61616";
    //主题名称
    private static final String topicName = "topic01_persist";

    public static void main(String[] args) throws JMSException {

        System.out.println("****consumer01****");

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.setClientID("consumer01");
        //

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(topicName);

        MessageConsumer consumer = session.createConsumer(topic);

        //创建一个持久的订阅者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "memo");

        connection.start();

        Message message = topicSubscriber.receive();
        while (message != null){
            TextMessage textMessage = (TextMessage) message;
            Date date = new Date();
            System.out.println("当前时间:"+date);
            System.out.println("收到持久化主题"+topicName+"发送的消息："+textMessage.getText());
            message = topicSubscriber.receive(3000L);
        }

        consumer.close();
        session.close();
        connection.close();
    }
}