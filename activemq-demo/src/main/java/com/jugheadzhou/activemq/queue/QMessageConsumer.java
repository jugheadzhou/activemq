package com.jugheadzhou.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.print.attribute.standard.Destination;
import java.io.IOException;

/**
 * @description: 队列模式 消息消费者
 * @author: aishu
 * @create: 2019-10-21 11:21
 */
public class QMessageConsumer {
    //默认值是 "failover://tcp://localhost:61616"
    private static final String brokerURL = "failover://tcp://localhost:61616";

    //队列名称
    private static final String queueName = "queue01";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue destination = session.createQueue(queueName);

        MessageConsumer consumer = session.createConsumer(destination);

        /**
         * 异步非阻塞方式（监听器onMessage()）
         * 订阅者或者接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器，
         * 当消息到达后，系统自动调用监听器MessageListener的onMessage(Message message)方法。
         */
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {

                    //接收TextMessage消息
                    if (message != null && message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("接收TextMessage消息：" + textMessage.getText());
                        System.out.println("接收消息属性：" + textMessage.getStringProperty("name"));
                        //手动签收
//                        textMessage.acknowledge();

                    }

                    //接收MapMessage消息
                    if (message != null && message instanceof MapMessage) {
                        MapMessage mapMessage = (MapMessage) message;
                        System.out.println("接收MapMessage消息：" + mapMessage.getString("k1"));
                    }

                    //接收ObjectMessage消息
                    if (message != null && message instanceof ObjectMessage) {
                        ObjectMessage objectMessage = (ObjectMessage) message;
                        System.out.println("接收ObjectMessage消息：" + objectMessage.getObject());
                    }


                } catch (JMSException j) {
                    System.out.println("获取消息发送异常：" + j.getMessage());
                }


            }
        });
        try {
            //
            System.in.read();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }

        consumer.close();
        session.close();
        connection.close();
    }
}