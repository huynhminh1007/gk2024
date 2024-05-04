package com.example.ltweb.domain.model;

import java.sql.Timestamp;

public class Log {

    private int id;
    private LogInfo level;
    private String address;
    private String ip;
    private String beforeValue;
    private String afterValue;
    private Timestamp dateCreate;

    public Log() {

    }

    public Log(LogInfo level, String address, String ip, String beforeValue, String afterValue) {
        this.level = level;
        this.address = address;
        this.ip = ip;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
    }

    public Log(int id, LogInfo level, String address, String ip, String beforeValue, String afterValue, Timestamp dateCreate) {
        this.id = id;
        this.level = level;
        this.address = address;
        this.ip = ip;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
        this.dateCreate = dateCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LogInfo getLevel() {
        return level;
    }

    public void setLevel(LogInfo level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
    }

    public String getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(String afterValue) {
        this.afterValue = afterValue;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public static enum LogInfo {
        INFO, ALERT, WARNING, DANGER;
    }
}
