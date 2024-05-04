package com.example.ltweb.domain.properties;

import java.io.IOException;
import java.util.Properties;

public class DBProperties {

    private static Properties properties = new Properties();

    public static String host;
    public static String port;
    public static String username;
    public static String password;
    public static String name;

    static {
        try {
            properties.load(DBProperties.class.getClassLoader().getResourceAsStream("DB.properties"));

            host = properties.getProperty("db.host");
            port = properties.getProperty("db.port");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            name = properties.getProperty("db.name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
