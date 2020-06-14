package com.study.springboot.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.UUID;

/**
 * program: java-learn->Producer
 * description:
 * author: gerry
 * created: 2020-06-01 22:07
 **/
@Component
public class Producer {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    /**
     * kafka对象
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 谷歌 gson
     */
    private static Gson gson = new GsonBuilder().create();

    /**
     * 发送消息,使用默认topic
     * @param message 消息体
     */
    public void sendMessage(Message message) {
        log.info("kafka sendMessage start");
        // 内部组织测试消息
        message.setId("KFK_"+System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());

        try {
            kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), gson.toJson(message));
        } catch (Exception e) {
            log.error("发送数据出错！！！{}{}", kafkaTemplate.getDefaultTopic(), gson.toJson(message));
            log.error("发送数据出错=====>", e);
        }

        // 消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
                log.info("数据发送成功完毕");
            }

            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                log.info("数据发送成功完毕");
            }

            @Override
            public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {
                log.info("数据发送失败完毕");
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                log.info("数据发送失败完毕");
            }
        });
        log.info("kafka sendMessage end");
    }

    /**
     * 发送消息，指定topic
     * @param topic kafka话题
     * @param data 字符串数据
     */
    public void sendMessage(String topic, String data) {
        log.info("kafka sendMessage start");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("kafka sendMessage success topic = {}, data = {}, result = {}",topic, data, result);
            }
        });
        log.info("kafka sendMessage end");
    }

}
