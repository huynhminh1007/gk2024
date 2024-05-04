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

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String gender = req.getParameter("gender");

        if(email != null && password != null && fullName != null && gender != null) {
            String message;

            if(UserService.getInstance().addUser(new User(email, password, fullName, gender))) {
                message = "Đăng ký thành công";
            }
            else {
                message = "Tài khoản đã được sử dụng";
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("message", message);

            JsonHelper.sendResponse(resp, HttpServletResponse.SC_OK, jsonObject.toString());
        }
    }
}
