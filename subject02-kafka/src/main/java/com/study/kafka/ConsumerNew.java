package com.study.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * program: java-learn->ConsumerNew
 * description:
 * author: gerry
 * created: 2020-06-01 23:07
 **/
public class ConsumerNew {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    public ConsumerNew(String topic) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "139.196.49.59:9092");
        // 记住 consumer 是需要依赖zk的
        props.put("zookeeper.connect", "139.196.49.59:2281");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // latest,earliest,none latest:读取最新的，earliest:从头开始
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

//        props.put("sasl.mechanism", "PLAIN");
//        props.put("security.protocol", "SASL_PLAINTEXT");
//        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"kafka\" password=\"kafkapasswd0102\";");

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    public void consumerMsg(){
        try {
            consumer.subscribe(Collections.singletonList(this.topic));
            System.out.println(consumer.listTopics());
//            while(true){
//
//            }

            ConsumerRecords<Integer, String> records = consumer.poll(2000);
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.println("*******************Received message: (" + record.key() + ", " + record.value() + ") at partition "+record.partition()+" offset " + record.offset());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsumerNew Consumer = new ConsumerNew("test2");
        Consumer.consumerMsg();
    }
}
