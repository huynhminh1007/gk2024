package com.example.ltweb.domain.service;

import com.example.ltweb.domain.dao.OrderDAO;
import com.example.ltweb.domain.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static OrderService instance;

    public static OrderService getInstance() {
        if (instance == null) {instance = new OrderService();}
        return instance;
    }

    public List<Order> findOrder(int limit, int offset, int orderBy, String orderDir, Map<String, Object> filters) {
        return OrderDAO.getOrder(limit, offset, orderBy, orderDir, filters);
    }

    public int getRecordsTotal() {
        return OrderDAO.getRecordsTotal();
    }

    public static int getRecordsTotal(Map<String, Object> filters) {
        return OrderDAO.getRecordsTotal(filters);
    }

    public static int update(int id, String column, Object newValue) {
        return OrderDAO.update(id, column, newValue);
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap();
        map.put("search", 17);
        System.out.println(OrderService.getInstance().update(1, "status", 1));
    }
}