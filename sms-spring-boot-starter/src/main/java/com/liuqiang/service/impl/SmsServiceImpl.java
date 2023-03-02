package com.liuqiang.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.liuqiang.entity.SMSProperties;
import com.liuqiang.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 短信服务
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SMSProperties smsProperties;

    //发送短信验证码
    @Override
    public String sendCode(String iphone) throws ClientException {
        if (StringUtils.isEmpty(iphone)){
            throw new RuntimeException("手机号码不能为空");
        }
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",smsProperties.getAccessKeyId() , smsProperties.getAccessSecret());
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(iphone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(smsProperties.getSignName());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsProperties.getTemplateCode());
        //生成随机的六位验证码
        String code = code();
        request.setTemplateParam("{\"code\":\"" + code + "\"}"); //您的验证码为 ${code} ，该验证码5分钟内有效，请勿泄露于他人。
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode()!=null && "OK".equals(sendSmsResponse.getCode())){
            System.out.println("短信发送成功");
            //发送成功验证码到存入redis中
        }else {
            throw new RuntimeException("短信发送失败:"+sendSmsResponse.getMessage());
        }
        return code;
    }
    //验证码生成器
    private String code(){
        Random random = new Random();
        //把随生成的数字转化为字符串
        String value = String.valueOf(random.nextInt(9));
        for (int i = 0; i <5; i++) {
            value+=random.nextInt(9);
        }
        return value;
    }
}
