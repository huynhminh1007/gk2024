package com.example.ltweb.controller.admin;

import com.example.ltweb.domain.model.Log;
import com.example.ltweb.domain.model.Order;
import com.example.ltweb.domain.service.LogService;
import com.example.ltweb.domain.service.OrderService;
import com.example.ltweb.domain.service.UserService;
import com.example.ltweb.utils.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/order-management")
public class OrderManageController extends HttpServlet {

    private final int totalRecords = UserService.getInstance().getRecordsTotal();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("order_management.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String edit = req.getParameter("edit");

        if(edit != null && !edit.isEmpty()) {
            String newValue = req.getParameter("newValue");
            int id = Integer.parseInt(req.getParameter("id"));
            int res;

            switch (edit) {
                case "status":
                    Order order = OrderService.findOrderById(id);
                    if(order != null) {
                        res = OrderService.update(id, edit, Integer.parseInt(newValue));

                        Log log = new Log();
                        log.setIp(req.getRemoteAddr());
                        log.setLevel(Log.LogInfo.WARNING);
                        log.setBeforeValue(new Gson().toJson(order));
                        order.setStatus(Integer.parseInt(newValue));
                        log.setAfterValue(new Gson().toJson(order));
                        log.setAddress("Logs");

                        LogService.log(log);
                    }
                    break;
                default: return;
            }

            return;
        }

        int limit = Integer.parseInt(req.getParameter("limit"));
        int offset = Integer.parseInt(req.getParameter("offset"));
        String searchValue = req.getParameter("search[value]");
        String orderBy = req.getParameter("order[0][column]");
        String orderDir = req.getParameter("order[0][dir]");

        Map<String, Object> filters = new HashMap<>();

        if(!searchValue.isEmpty()) {
            filters.put("search", searchValue);
        }
        if(orderBy == null || orderDir == null || orderBy.isEmpty() || orderDir.isEmpty()) {
            orderBy = "0";
            orderDir = "asc";
        }

        JsonObject jsonObject = new JsonObject();
        int totalRecordsFiltered = OrderService.getRecordsTotal(null);

        if(totalRecordsFiltered != 0) {
            List<Order> orderList = OrderService.getInstance()
                    .findOrder(limit, offset, Integer.parseInt(orderBy) + 1, orderDir, filters);

            jsonObject.addProperty("recordsFiltered", totalRecordsFiltered);
            jsonObject.add("orderList", new Gson().toJsonTree(orderList).getAsJsonArray());
        }

        jsonObject.addProperty("recordsTotal", totalRecords);

        JsonHelper.sendResponse(resp, HttpServletResponse.SC_OK, jsonObject.toString());
    }
}
