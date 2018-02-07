package com.geetion.catcher.gateway.config;

import com.geetion.catcher.gateway.service.IEmqService;
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
    private IEmqService emqService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        emqService.initMqConfig();
       // 开启MQTT
       if (emqService.connectSubMq() && emqService.connectPubMq()) {
            logger.info("开启MQTT成功");
        }
    }


}
