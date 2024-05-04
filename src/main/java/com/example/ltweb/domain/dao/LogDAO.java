package com.example.ltweb.domain.dao;

import com.example.ltweb.domain.model.Log;
import com.example.ltweb.domain.model.User;
import com.example.ltweb.domain.properties.JDBIConnector;
import com.google.gson.Gson;

import java.util.List;

public class LogDAO {

    public static int log(Log log) {
        return JDBIConnector.getInstance().withHandle(handle -> (
                handle.createUpdate("INSERT INTO logs (level, address, ip, beforeValue, afterValue) "
                + "VALUES (:level, :address, :ip, :beforeValue, :afterValue)")
                        .bind("level", log.getLevel().toString())
                        .bind("address", log.getAddress())
                        .bind("ip", log.getIp())
                        .bind("beforeValue", log.getBeforeValue())
                        .bind("afterValue", log.getAfterValue())
                        .execute()
                ));
    }

    public static List<Log> getLogs(int limit, int offset) {
        return JDBIConnector.getInstance().withHandle(handle -> (
                handle.createQuery("SELECT * FROM logs LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Log.class)
                        .list()
                ));
    }

    public static void main(String[] args) {
        User user = new User("email", "password", "fullName", "gender");

        Log log = new Log();
        log.setIp("127.0.0.1");
        log.setAddress("Users");
        log.setLevel(Log.LogInfo.INFO);
        log.setAfterValue(new Gson().toJson(user));

        System.out.println(log(log));
    }
}
