package com.baomidou.springboot.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springboot.Util.MD5Util;
import com.baomidou.springboot.entity.UcenterUser;
import com.baomidou.springboot.service.IUcenterUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Autowired
    private IUcenterUserService ucenterUserService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        String inPassword = new String(utoken.getPassword());
        String loginUserName = utoken.getUsername();
        UcenterUser user =
                ucenterUserService.selectOne(new EntityWrapper<UcenterUser>()
                        .where("delete_flag={0} and login_name={1}", "NORMAL", loginUserName));
        String salt = user.getSalt();
        String md5Str = inPassword + salt;
        String isPassword = MD5Util.getMD5(md5Str);
        String dbPassword=(String) info.getCredentials();
        return this.equals(isPassword, dbPassword);
    }
}
