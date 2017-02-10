package com.weibo.ad.example.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table()
public class GuardApiEntity implements Serializable {

    @Column()
    private Integer id;
    private String hash;
    private String name;
    private String short_name;
    private String desc;
    private String heartbeats_url;
    private String get_url;
    private String callback_url;
    private String callback_encryption_key;
    private String key;
    private String ip;
    private String mode;
    private Integer time_limit;
    private int status;
    private Date update_time;
    private int update_user_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHeartbeats_url() {
        return heartbeats_url;
    }

    public void setHeartbeats_url(String heartbeats_url) {
        this.heartbeats_url = heartbeats_url;
    }

    public String getGet_url() {
        return get_url;
    }

    public void setGet_url(String get_url) {
        this.get_url = get_url;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getCallback_encryption_key() {
        return callback_encryption_key;
    }

    public void setCallback_encryption_key(String callback_encryption_key) {
        this.callback_encryption_key = callback_encryption_key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Integer time_limit) {
        this.time_limit = time_limit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(int update_user_id) {
        this.update_user_id = update_user_id;
    }

    @Override
    public String toString() {
        return "GuardApiEntity{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", name='" + name + '\'' +
                ", short_name='" + short_name + '\'' +
                ", desc='" + desc + '\'' +
                ", heartbeats_url='" + heartbeats_url + '\'' +
                ", get_url='" + get_url + '\'' +
                ", callback_url='" + callback_url + '\'' +
                ", callback_encryption_key='" + callback_encryption_key + '\'' +
                ", key='" + key + '\'' +
                ", ip='" + ip + '\'' +
                ", mode='" + mode + '\'' +
                ", time_limit=" + time_limit +
                ", status=" + status +
                ", update_time=" + update_time +
                ", update_user_id=" + update_user_id +
                '}';
    }
}
