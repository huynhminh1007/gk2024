package com.example.ltweb.domain.model;

public class User {

    private int id;
    private String email;
    private String password;
    private String fullName;
    private String gender;

    public User() {

    }

    public User(int id, String email, String password, String fullName, String gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
    }

    public User(String email, String password, String fullName, String gender) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
