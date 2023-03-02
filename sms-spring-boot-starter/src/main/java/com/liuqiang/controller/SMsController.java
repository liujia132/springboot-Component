package com.liuqiang.controller;


import com.aliyuncs.exceptions.ClientException;
import com.liuqiang.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信服务接口
 */
@RestController
@RequestMapping("/sms")
public class SMsController {
    @Autowired
    private SmsService smsService;


    @GetMapping("/sendCode/{iphone}")
    public String sendCode(@PathVariable("iphone") String iphone) throws ClientException {
        String code = smsService.sendCode(iphone);
        if (StringUtils.isEmpty(code)){
            System.out.println("code:"+code);
            return "发送短信成功";
        }
        return code;
    }

}
