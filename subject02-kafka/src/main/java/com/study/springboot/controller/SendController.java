package com.study.springboot.controller;

import com.study.springboot.producer.Message;
import com.study.springboot.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * program: java-learn->SendController
 * description:
 * author: gerry
 * created: 2020-06-01 06:07
 **/
@RestController
@RequestMapping("kafka")
public class SendController {

    @Autowired
    private Producer producer;

    @GetMapping("send")
    public String send() {
        producer.sendMessage(new Message());
        return "{\"code\":0}";
    }
}
