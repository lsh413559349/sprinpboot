package com.baomidou.springboot.controller;

import com.baomidou.springboot.Util.CheckoutUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/wx")
public class WeiXinController {
    @GetMapping("")
    public String chcekToken(
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "echostr") String echostr,
            @RequestParam(value = "timestamp")  String timestamp,
            @RequestParam(value = "nonce") String nonce){
        System.out.println(signature+" "+echostr+" "+timestamp+" "+timestamp+" "+nonce);
        try {
            // SHA1加密
            boolean sign = CheckoutUtil.checkSignature(signature, timestamp, nonce);
            if(signature != null && sign){
                return echostr;
            }
            // 确认请求来至微信
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
