package com.liuqiang.service.impl;

import com.liuqiang.entity.MailProperties;
import com.liuqiang.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.Objects;
/**
 * 邮件服务
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    //发送普通的邮件
    @Override
    public void sendSimpleMailMessage() {
        //构建发送的对象
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发件人
        message.setFrom(mailProperties.getFrom());
        //设置收件人
        message.setTo(mailProperties.getTo());
        //设置标题
        message.setSubject("测试邮件");
        //设置消息的内容
        message.setText("我想听徐誉滕的绿色火车");

        //执行发送消息
        javaMailSender.send(message);

    }
    // 发送html邮件
    @Override
    public void sendMimeMessage() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        //使用该方法构建message
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(mailProperties.getFrom());
        messageHelper.setTo(mailProperties.getTo());
        messageHelper.setSubject(mailProperties.getSubject());
        //设置解析内容中的html
        messageHelper.setText(mailProperties.getText(),true);
        javaMailSender.send(message);

    }
    // 发送带附件的邮件
    @Override
    public void sendMimeMessage( String filePath) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        // true表示需要创建一个multipart message
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
        messageHelper.setFrom(mailProperties.getFrom());
        messageHelper.setTo(mailProperties.getTo());
        messageHelper.setSubject(mailProperties.getSubject());
        messageHelper.setText(mailProperties.getText());
        FileSystemResource file = new FileSystemResource(new File(filePath));
        messageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
        javaMailSender.send(message);

    }

    // 发送带静态文件的邮件
    @Override
    public void sendMimeMessage( Map<String, String> rscIdMap) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(mailProperties.getFrom());
        helper.setTo(mailProperties.getTo());
        helper.setTo(mailProperties.getText());
        //遍历集合
        for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
            FileSystemResource resource = new FileSystemResource(new File(entry.getValue()));
            helper.addInline(entry.getKey(),resource);
        }

        javaMailSender.send(message);



    }
}
