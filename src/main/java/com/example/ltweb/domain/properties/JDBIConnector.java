package com.example.ltweb.domain.properties;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;

public class JDBIConnector {

    private static Jdbi jdbi;

    private static void connect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://" + DBProperties.host + ":" + DBProperties.port + "/" + DBProperties.name);
        dataSource.setUser(DBProperties.username);
        dataSource.setPassword(DBProperties.password);

        try {
            dataSource.setAutoReconnect(true);
            dataSource.setUseCompression(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        jdbi = Jdbi.create(dataSource);
    }

    public static Jdbi getInstance() {
        if(jdbi == null) {
            connect();
        }
        return jdbi;
    }
}
