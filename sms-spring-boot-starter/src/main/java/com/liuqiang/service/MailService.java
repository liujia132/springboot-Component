package com.liuqiang.service;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService {
     void sendSimpleMailMessage();
     void sendMimeMessage() throws MessagingException;
     void sendMimeMessage( String filePath) throws MessagingException;
     void sendMimeMessage( Map<String,String> rscIdMap) throws MessagingException;

}
