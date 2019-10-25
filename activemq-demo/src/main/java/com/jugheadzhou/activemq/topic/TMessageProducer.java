package com.jugheadzhou.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description: 主题模式 消息生产者
 * @author: aishu
 * @create: 2019-10-21 11:42
 */
public class TMessageProducer {
    //默认值是 "failover://tcp://localhost:61616"
    private static final String brokerURL = "failover://tcp://localhost:61616";
    //主题名称
    private static final String topicName = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topic = session.createTopic(topicName);

        MessageProducer producer = session.createProducer(topic);

        for (int i = 0; i < 50; i++) {
            //发送消息
            TextMessage textMessage = session.createTextMessage(topicName + "主题生产第" + i + "条消息");
            producer.send(textMessage);
            System.out.println(topicName + "主题生产第" + i + "条消息");
        }
        producer.close();
        session.close();
        connection.close();
    }
}