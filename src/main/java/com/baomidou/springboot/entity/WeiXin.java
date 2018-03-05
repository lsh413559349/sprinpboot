package com.baomidou.springboot.entity;

public class WeiXin {
    private String signature;
    private String echostr;
    private String timestamp;
    private String nonce;

    public WeiXin(String signature, String echostr, String timestamp, String nonce) {
        this.signature = signature;
        this.echostr = echostr;
        this.timestamp = timestamp;
        this.nonce = nonce;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return "WeiXin{" +
                "signature='" + signature + '\'' +
                ", echostr='" + echostr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                '}';
    }
}
