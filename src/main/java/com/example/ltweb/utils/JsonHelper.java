package com.example.ltweb.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JsonHelper {

    public static void sendResponse(HttpServletResponse resp, int status, String message) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(status);
        resp.getWriter().write(message);
    }
}
