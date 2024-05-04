package com.example.ltweb.domain.model;

import java.sql.Timestamp;

public class Order {

    private int id;
    private int userId;
    private int productId;
    private int amount;
    private int status;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;

    public Order() {

    }

    public Order(int id, int userId, int productId, int amount, int status, Timestamp dateCreate, Timestamp dateUpdate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.status = status;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
