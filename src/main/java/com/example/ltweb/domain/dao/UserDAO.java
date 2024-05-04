package com.example.ltweb.domain.dao;

import com.example.ltweb.domain.model.User;
import com.example.ltweb.domain.properties.JDBIConnector;

import java.util.List;
import java.util.Optional;

public class UserDAO {

    public static User getUserByEmail(final String email) {
        Optional<User> user = JDBIConnector.getInstance().withHandle((handle ->
                handle.createQuery("SELECT * FROM users WHERE email = :email")
                        .bind("email", email)
                        .mapToBean(User.class)
                        .stream().findFirst()
        ));
        return user.isEmpty() ? null : user.get();
    }

    public static int create(User user) {
        if (getUserByEmail(user.getEmail()) != null) {
            return 0;
        }

        return JDBIConnector.getInstance().withHandle(handle ->
                handle.createUpdate("INSERT INTO users (email, password, fullName, gender) VALUES (:email, :password, :fullName, :gender)")
                        .bind("email", user.getEmail())
                        .bind("password", user.getPassword())
                        .bind("fullName", user.getFullName())
                        .bind("gender", user.getGender())
                        .execute()
        );
    }

    public static List<User> getUser(int limit, int offset) {
        return JDBIConnector.getInstance().withHandle(handle ->
                handle.createQuery("SELECT * FROM users LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(User.class)
                        .list()
        );
    }

    public static int getRecordsTotal() {
        return JDBIConnector.getInstance().withHandle(handle ->
                handle.createQuery("SELECT COUNT(id) FROM users")
                        .mapTo(Integer.class)
                        .one()
                );
    }
}
