package com.jugheadzhou.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * @description: 主题模式 持久化消息生产者
 * @author: aishu
 * @create: 2019-10-21 11:42
 */
public class TPersistMessageProducer {
    //默认值是 "failover://tcp://localhost:61616"
    private static final String brokerURL = "failover://tcp://localhost:61616";
    //主题名称
    private static final String topicName = "topic01_persist";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = activeMQConnectionFactory.createConnection();

//        //未开启持久之前的位置
//        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topic = session.createTopic(topicName);

        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //开启持久化，需要将连接开始方式放在这里
        connection.start();

        for (int i = 0; i < 3; i++) {
            //发送消息
            Date date = new Date();
            TextMessage textMessage = session.createTextMessage(topicName + "主题生产第" + i + "条持久化消息,时间是："+ date);
            producer.send(textMessage);
            System.out.println(topicName + "主题生产第" + i + "条持久化消息");
        }
        producer.close();
        session.close();
        connection.close();
    }
}