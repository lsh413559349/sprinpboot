package com.baomidou.springboot.config;

import com.baomidou.springboot.service.IEMQtest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

/**
 * Created by virgilyan on 2017/11/14.
 */
@Configuration
@EnableAsync
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent> {

    protected final static Logger logger = LoggerFactory.getLogger(ApplicationEvent.class);

    @Resource
    private IEMQtest emqService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        emqService.initMqConfig();
        logger.info("MQTT开启中");
       // 开启MQTT
       if (emqService.connectSubMq() ) {
            logger.info("开启MQTT成功");
        }
    }


}
