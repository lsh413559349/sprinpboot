package com.baomidou.springboot.shiro;


import com.baomidou.springboot.entity.enums.MapDbEnum;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CredentialsMatcher extends SimpleCredentialsMatcher {
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(100);
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger atomicInteger =(AtomicInteger) MapDbEnum.MAPDB.get(username);
        if(atomicInteger == null){
            atomicInteger = new AtomicInteger(0);
            MapDbEnum.MAPDB.put(username,atomicInteger);
        }
        if(atomicInteger.incrementAndGet()>3){
           /* //10分钟后消除
            executorService.schedule(()->{
                MapDbEnum.MAPDB.remove(username);
            },1000*10, TimeUnit.MILLISECONDS);*/
            throw new ExcessiveAttemptsException("连续登录超过3次密码不正确，请10秒后登录");
        }
        boolean b = super.doCredentialsMatch(token, info);
        if(b){
            MapDbEnum.MAPDB.remove(username);
        }else{
            MapDbEnum.MAPDB.put(username,atomicInteger);
        }
        return b;
    }
}
