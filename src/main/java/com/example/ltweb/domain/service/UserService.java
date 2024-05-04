package com.example.ltweb.domain.service;

import com.example.ltweb.domain.dao.UserDAO;
import com.example.ltweb.domain.model.User;

import java.util.List;

public class UserService {

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User isValidUser(String email, String password) {
        User user = UserDAO.getUserByEmail(email);

        if(user != null) {
            if(email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean addUser(User user) {
        return UserDAO.create(user) == 1;
    }

    public List<User> findUser(int limit, int offset) {
        return UserDAO.getUser(limit, offset);
    }

    public int getRecordsTotal() {
        return UserDAO.getRecordsTotal();
    }

    public static void main(String[] args) {
        System.out.println(UserService.getInstance().getRecordsTotal());
    }
}
