package com.baomidou.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by virgilyan on 2017/11/10.
 */
@Configuration
@ConfigurationProperties("mqtt-config")
public class MQTTConfig {

    public static final String PREFIX_APP_CONTROL = "app/";

    public static final String PREFIX_DEVICE_CONTROL = "device/";

    public static final String DEVICE_CONTROL_GAME = "/game";

    public static final String DEVICE_CONTROL_REPLY = "/reply";

    public static final String DEVICE_CONTROL_PUBLIC = PREFIX_DEVICE_CONTROL + "public";

    public static final String GATEWAY_RECEIVE_MSG = "gateway/";

    public static final String APP_CONTROL_SERVICE = "/service";

    public static final String APP_QUEUE_SERVICE = "/queue";

    private String host;

    private int port;

    private String clientId;

    private String username;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
