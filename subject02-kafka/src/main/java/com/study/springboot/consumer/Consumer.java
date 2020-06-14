package com.study.springboot.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * program: java-learn->Consumer
 * description:
 * author: gerry
 * created: 2020-06-01 22:26
 **/
@Component
public class Consumer {
    @KafkaListener(topics = {"test2", "test"})
    public void processMessage(String content) {
        System.out.println("消息被消费" + content);
    }
}
