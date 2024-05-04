package com.example.ltweb.controller;

import com.example.ltweb.domain.model.User;
import com.example.ltweb.domain.service.UserService;
import com.example.ltweb.utils.JsonHelper;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email != null && password != null) {
            String message;
            int status;
            JsonObject jsonObject = new JsonObject();

            User user = UserService.getInstance().isValidUser(email, password);

            if(user != null) {
                message = "Đăng nhập thành công";
                status = HttpServletResponse.SC_OK;
            }
            else {
                message = "Đăng nhập thất bại";
                status = HttpServletResponse.SC_OK;
            }

            jsonObject.addProperty("message", message);

            JsonHelper.sendResponse(resp, status, jsonObject.toString());
        }
    }
}
