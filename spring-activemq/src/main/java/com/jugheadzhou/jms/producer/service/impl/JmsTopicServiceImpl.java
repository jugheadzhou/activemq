package com.jugheadzhou.jms.producer.service.impl;

import com.jugheadzhou.jms.producer.service.IJmsTopicService;
import com.jugheadzhou.jms.producer.thread.TopicThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: aishu
 * @create: 2019-10-24 10:33
 */
@Slf4j
@Service
public class JmsTopicServiceImpl implements IJmsTopicService {
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired(required = false)
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
    /**
     * 生产者发送主题订阅消息
     *
     * @param destination 目的地名称
     * @param message 发送的具体消息
     */
    @Override
    public void send(String destination, Object message) {

        if (this.jmsTemplate == null) return;

        TopicThread topicThread = new TopicThread(this.jmsTemplate, destination, message);

        log.info("开始执行主题线程,目的地是{}",destination);
        this.executorService.execute(topicThread);
    }

    /**
     *
     * 提供给子类设置JmsTemplate 扩展功能
     * @param jmsTemplate
     */
    public void setTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}