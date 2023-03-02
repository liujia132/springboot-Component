package com.liuqiang.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    //发件人
    private String from;
    //收件人
    private String to;
    //标题
    private String subject;
    //内容
    private String text;
}
