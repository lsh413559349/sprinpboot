package com.baomidou.springboot.controller;

import com.baomidou.springboot.entity.UcenterUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("")
public class LoginController {
    @PostMapping("/loginUser")
    public String loginUser(@RequestBody UcenterUser ucenterUser, HttpSession session) {
        UsernamePasswordToken usernamePasswordToken=
                new UsernamePasswordToken(ucenterUser.getLoginName(),ucenterUser.getPassWork());
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
