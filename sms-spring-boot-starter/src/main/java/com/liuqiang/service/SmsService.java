package com.liuqiang.service;


import com.aliyuncs.exceptions.ClientException;

public interface SmsService {
    String sendCode(String iphone) throws ClientException;

}
