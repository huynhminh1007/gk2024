package com.example.ltweb.domain.service;

import com.example.ltweb.domain.dao.LogDAO;
import com.example.ltweb.domain.model.Log;

import java.util.List;

public class LogService {

    private static LogService instance;

    public static LogService getInstance() {
        if (instance == null) {
            instance = new LogService();
        }
        return instance;
    }

    public static int log(Log log) {
        return LogDAO.log(log);
    }

    public static List<Log> getLogs(int limit, int offset) {
        return LogDAO.getLogs(limit, offset);
    }

    public static void main(String[] args) {

    }
}
