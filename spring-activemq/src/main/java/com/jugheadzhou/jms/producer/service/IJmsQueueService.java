package com.jugheadzhou.jms.producer.service;

public interface IJmsQueueService {
    /**
     * 生产者发送队列订阅消息
     * @param destination 目的地名称
     * @param message 发送的具体消息
     */
    void send(String destination, Object message);
}
