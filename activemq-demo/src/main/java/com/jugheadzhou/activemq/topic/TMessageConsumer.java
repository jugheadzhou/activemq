package com.jugheadzhou.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @description: 主题模式 消息消费者
 * @author: aishu
 * @create: 2019-10-21 11:42
 */
public class TMessageConsumer {
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

        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("接收主题是"+topicName+"的消息："+textMessage.getText());
                }catch (JMSException j){
                    System.out.println("接收消息异常："+j.getMessage());
                }
            }
        });
        try {
            //
            System.in.read();
        }catch (IOException i){
            System.out.println(i.getMessage());
        }

        consumer.close();
        session.close();
        connection.close();
    }
}