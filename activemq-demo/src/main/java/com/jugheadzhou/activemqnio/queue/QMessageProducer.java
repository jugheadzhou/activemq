package com.jugheadzhou.activemqnio.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

/**
 * @description: 队列模式 消息生产者
 * @author: aishu
 * @create: 2019-10-21 10:46
 */
public class QMessageProducer {

    //默认值是 "failover://tcp://localhost:61616" 这里使用NIO协议
    private static final String brokerURL = "failover://nio://localhost:61618";

    //队列名称
    private static final String queueName = "queue_nio";

    public static void main(String[] args) throws JMSException {

        //创建mq连接工场
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);

        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();

        //打开连接
        connection.start();

        /**
         * 创建会话 事务偏生产者，签收偏消费者
         * @prame transacted 事务 false关闭事务，true开启事务，如果设置为true，必须在session关闭之前提交事务session.commit();
         * @prames acknowledgeMode 签收
         *     int AUTO_ACKNOWLEDGE = 1; 自动签收 常用
         *     int CLIENT_ACKNOWLEDGE = 2; 手动签收 常用 必须在每次收到消息手动签收textMessage.acknowledge();
         *     int DUPS_OK_ACKNOWLEDGE = 3; 允许重复签收
         *     int SESSION_TRANSACTED = 0; 事务级的签收，开启事务后选择此签收方式
         *
         * 如果设置了开启事务而没有session.commit()，就算设置了手动签收，消息也会被重复消费。
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建目的地Destination->Queue
        Destination destination = session.createQueue(queueName);

        //创建生产者
        MessageProducer messageProducer = session.createProducer(destination);

//        //设置消息非持久化：当服务器宕机，消息不存在
//        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//        //设置消息持久化：当服务器宕机，消息依然存在，默认是持久化的
//        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);


        //模拟创建10个消息
        for (int i = 0; i < 50; i++) {
            //发送TextMessage消息
            TextMessage textMessage = session.createTextMessage("第" + i + "条消息;");
            textMessage.setStringProperty("name","张"+i);
            messageProducer.send(textMessage);
            System.out.println("发送TextMessage消息："+textMessage.getText());

//            //发送MapMessage消息
//            MapMessage mapMessage = session.createMapMessage();
//            mapMessage.setString("k1","v1");
//            messageProducer.send(mapMessage);
//            System.out.println("发送MapMessage消息："+textMessage.getText());
//
//            //发送对象消息
//            User user = new User(1L,"小明",9);
//            ObjectMessage objectMessage = session.createObjectMessage();
//            objectMessage.setObject(user);
//            messageProducer.send(objectMessage);
        }

        //关闭连接
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("发送消息完成。");
    }
}


class User implements Serializable {
    private Long id;
    private String name;
    private int age;

    public User() {
    }

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}