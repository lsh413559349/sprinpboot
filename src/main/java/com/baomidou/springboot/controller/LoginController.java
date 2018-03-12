package com.baomidou.springboot.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springboot.Util.MD5Util;
import com.baomidou.springboot.entity.UcenterUser;
import com.baomidou.springboot.entity.enums.MapDbEnum;
import com.baomidou.springboot.service.IUcenterUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private IUcenterUserService ucenterUserService;
    /**
     * 登录验证
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/loginUser")
    public Object loginUser(@RequestBody UcenterUser user, HttpSession session) {
        UcenterUser ucenterUser =
                ucenterUserService.selectOne(new EntityWrapper<UcenterUser>()
                        .where("login_name = {0} and delete_flag={1}",
                                user.getLoginName(), "NORMAL"));
        String passWord = MD5Util.getMD5(user.getPassWord()+ucenterUser.getSalt());
        //设置token
        UsernamePasswordToken usernamePasswordToken=
                new UsernamePasswordToken(user.getLoginName(),passWord);
        Subject subject = SecurityUtils.getSubject();
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            UcenterUser cuser=(UcenterUser) subject.getPrincipal();
            session.setAttribute("user", cuser);
            return "true";
        } catch(DisabledAccountException e) {
            return "禁用的账号";
        }catch (ExcessiveAttemptsException l){
            return "登录失败次数过多";
        }catch (Exception  l){
            return "密码或账号错误";
        }

    }

    @GetMapping("/testMapDB")
    public Object testMapDB(){
        return MapDbEnum.MAPDB.get("admin");
    }

    @GetMapping("/removeMapDB")
    public Object testMapDB(@RequestParam(value = "key") String key){
        return MapDbEnum.MAPDB.remove(key);
    }

}
