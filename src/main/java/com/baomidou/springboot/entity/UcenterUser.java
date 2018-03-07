package com.baomidou.springboot.entity;

import java.util.Date;

public class UcenterUser {
    private Integer id;
    private String loginName;
    private String passWork;
    private String salt;
    private String nickname;
    private Integer sex;
    private String avatar;
    private Integer count;
    private Date lastLoginTime;
    private String lastLoginIp;
    private Date created;
    private Date updated;
    private String deleteFlag;

    public UcenterUser(Integer id, String loginName, String passWork, String salt, String nickname, Integer sex, String avatar, Integer count,
                       Date lastLoginTime, String lastLoginIp, Date created, Date updated, String deleteFlag) {
        this.id = id;
        this.loginName = loginName;
        this.passWork = passWork;
        this.salt = salt;
        this.nickname = nickname;
        this.sex = sex;
        this.avatar = avatar;
        this.count = count;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIp = lastLoginIp;
        this.created = created;
        this.updated = updated;
        this.deleteFlag = deleteFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWork() {
        return passWork;
    }

    public void setPassWork(String passWork) {
        this.passWork = passWork;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "UcenterUser{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", passWork='" + passWork + '\'' +
                ", salt='" + salt + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                ", count=" + count +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
