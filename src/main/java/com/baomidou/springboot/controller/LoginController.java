package com.baomidou.springboot.controller;

import com.baomidou.springboot.entity.UcenterUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam(value="username") String username,
                            @RequestParam(value="password") String password, HttpSession session) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            UcenterUser user=(UcenterUser) subject.getPrincipal();
            session.setAttribute("user", user);
            return "true";
        } catch(Exception e) {
            return "false";
        }

    }
}
