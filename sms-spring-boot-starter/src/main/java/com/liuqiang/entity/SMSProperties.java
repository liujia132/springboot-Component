package com.liuqiang.entity;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@ConfigurationProperties(prefix = "sms")
public class SMSProperties implements Serializable {
    private String accessKeyId;
    private String accessSecret;
    private String signName;
    private String templateCode;
}
