package com.liuqiang.controller;

import com.liuqiang.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/sendSimple")
    public void sendSimpleMailMessage(){
        mailService.sendSimpleMailMessage();
        System.out.println("发送邮件成功");
    }


}
