package com.baomidou.springboot.service.impl;


import com.baomidou.springboot.config.MQTTConfig;
import com.baomidou.springboot.service.IEMQtest;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_DEFAULT;

@Service
public class EMQtestImpl implements IEMQtest{
    /**
     * MQTT-Client
     */
    private MqttClient mqttSubClient;
    private MqttConnectOptions mqSubOptions = new MqttConnectOptions();
    private String uri;
    @Resource
    private MQTTConfig mqttConfig;
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(100);

    private void clientDisconnected() {

        subscribe("/test", 2,
                (topic, message) -> handlerDisconnectedMessage(topic, message));
    }

    @Async
    public void subscribe(String topic, int qos, IMqttMessageListener callback) {
        try {
            mqttSubClient.subscribeWithResponse(topic, qos, callback).waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void handlerDisconnectedMessage(String topic, MqttMessage message) {
        System.out.println("topic:"+topic);
        System.out.println("message:"+message);
        publish("/testTo",2,"收到你的信息");
    }
    @Async
    public void publish(String topic, int qos, String msg) {
        executorService.execute(() -> {
            try {
                MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
                mqttMessage.setQos(qos);
                mqttSubClient.publish(topic, mqttMessage);
                System.out.println("发送: topic="+topic+" msg:"+msg);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    public void initMqConfig() {
        uri = "tcp://" + mqttConfig.getHost() + ":" + mqttConfig.getPort();
        mqSubOptions = new MqttConnectOptions();
        mqSubOptions.setServerURIs(new String[]{uri});
        mqSubOptions.setUserName(mqttConfig.getUsername());
       // mqSubOptions.setPassword(mqttConfig.getPassword().toCharArray());
        mqSubOptions.setCleanSession(false);
        mqSubOptions.setKeepAliveInterval(1);
        mqSubOptions.setMaxInflight(10);
        mqSubOptions.setAutomaticReconnect(true);
        mqSubOptions.setMqttVersion(MQTT_VERSION_DEFAULT);

    }

    @Override
    public boolean connectSubMq() {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            mqttSubClient = new MqttClient(uri, mqttConfig.getClientId() + "_pub", persistence, new ScheduledThreadPoolExecutor(100));
            mqttSubClient.setCallback(new MqttCallbackExtended() {

                @Override
                public void connectionLost(Throwable cause) {
                    // Automatic reconnect is set so make sure comms is in resting state
                    pubReconnect();
                    System.out.println("发送服务器下线");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }

                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    if (mqttSubClient.isConnected()) {
                        System.out.println("监听成功");
                    } else {
                        System.out.println("监听失败");
                    }
                }
            });
            mqttSubClient.connectWithResult(mqSubOptions).waitForCompletion();
            clientDisconnected();
        } catch (MqttException e) {
            System.out.println("异常信息" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return isPubConnected();
    }

    public boolean pubReconnect() {
        try {
            mqttSubClient.reconnect();
        } catch (MqttException e) {
            System.out.println("异常信息" + e.getMessage());
            e.printStackTrace();
        }
        return isPubConnected();
    }

    public boolean isPubConnected() {
        return mqttSubClient != null ? mqttSubClient.isConnected() : false;
    }
}
