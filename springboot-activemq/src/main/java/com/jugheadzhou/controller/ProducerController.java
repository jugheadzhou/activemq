package com.jugheadzhou.controller;

import com.jugheadzhou.activemq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: aishu
 * @create: 2019-10-25 10:35
 */
@RestController
@RequestMapping("activemq")
public class ProducerController {
    @Autowired
    private Producer producer;

    @GetMapping("queue")
    public String sendQueue(String message) {
        return this.producer.sendQueue(message);
    }

    @GetMapping("topic")
    public String sendTopic(String message) {
        return this.producer.sendTopic(message);
    }
}