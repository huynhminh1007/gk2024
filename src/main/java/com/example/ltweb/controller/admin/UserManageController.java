package com.example.ltweb.controller.admin;

import com.example.ltweb.domain.model.User;
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
import java.util.List;

@WebServlet("/user-management")
public class UserManageController extends HttpServlet {

    private int totalRecords = UserService.getInstance().getRecordsTotal();
    private final int LIMIT = 100;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user_management.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = Integer.parseInt(req.getParameter("limit"));
        int offset = Integer.parseInt(req.getParameter("offset"));

        String searchValue = req.getParameter("search[value]");
        String orderBy = req.getParameter("order[0][column]");
        String orderDir = req.getParameter("order[0][dir]");

        List<User> userList = UserService.getInstance().findUser(limit, offset);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("recordsTotal", totalRecords);

        if (userList != null && !userList.isEmpty()) {

            jsonObject.addProperty("recordsFiltered", 100);
            jsonObject.add("userList", new Gson().toJsonTree(userList).getAsJsonArray());
        }

        JsonHelper.sendResponse(resp, HttpServletResponse.SC_OK, jsonObject.toString());
    }
}
