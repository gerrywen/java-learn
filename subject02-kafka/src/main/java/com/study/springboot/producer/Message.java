package com.study.springboot.producer;

import java.util.Date;

/**
 * program: java-learn->Message
 * description:
 * author: gerry
 * created: 2020-06-01 22:06
 **/
public class Message {
    private String id;

    private String msg;

    private Date sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
