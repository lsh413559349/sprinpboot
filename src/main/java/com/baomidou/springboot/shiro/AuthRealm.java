package com.baomidou.springboot.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springboot.entity.UcenterUser;
import com.baomidou.springboot.service.IUcenterUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUcenterUserService ucenterUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String loginUserName = utoken.getUsername();
        UcenterUser user = ucenterUserService.
                selectOne(new EntityWrapper<UcenterUser>().where("delete_flag={0} and login_name={1}", "NORMAL", loginUserName));
        return new SimpleAuthenticationInfo(user, user.getPassWord(),this.getClass().getName());
    }
}
