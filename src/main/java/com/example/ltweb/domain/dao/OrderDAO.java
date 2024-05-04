package com.example.ltweb.domain.dao;

import com.example.ltweb.domain.model.Order;
import com.example.ltweb.domain.properties.JDBIConnector;

import java.util.List;
import java.util.Map;

public class OrderDAO {

    public static List<Order> getOrder(int limit, int offset, int orderBy, String orderDir, Map<String, Object> filters) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM orders");

        if(filters != null && !filters.isEmpty()) {
            appendFilterQuery(query, filters);
        }
        query.append(" ORDER BY :order ")
            .append(orderDir + " LIMIT :limit OFFSET :offset");

        return JDBIConnector.getInstance().withHandle(handle ->
                    handle.createQuery( query)
                            .bind("order", orderBy)
                            .bind("limit", limit)
                            .bind("offset", offset)
                            .mapToBean(Order.class)
                            .list()
                );
    }

    public static int getRecordsTotal() {
        return JDBIConnector.getInstance().withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM orders")
                        .mapTo(Integer.class)
                        .one()
                );
    }

    public static int getRecordsTotal(Map<String, Object> filters) {
        return JDBIConnector.getInstance().withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM orders")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    private static void appendFilterQuery(StringBuilder query, Map<String, Object> filters) {
        query.append(" WHERE");
        filters.forEach((k, v) -> {
            switch (k) {
                case "search":
                    query.append(" " + v + " IN (id, userId, productId, amount, status)");
                    break;
            }
        });
    }

    public static int update(int id, String column, Object newValue) {
        return JDBIConnector.getInstance().withHandle(handle ->
                handle.createUpdate("UPDATE orders SET " + column + " = :newValue WHERE id = :id")
                        .bind("id", id)
                        .bind("newValue", newValue)
                        .execute()
        );
    }
}
