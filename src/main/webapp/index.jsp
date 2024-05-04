<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        button {
            padding: 10px 0px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div id="wrapper">
        <section class="container" style="display: flex; flex-direction: column; padding: 0 30%">
            <button id="login">Đăng nhập</button>
            <button id="sign-up">Đăng ký</button>
            <button id="user-management">Quản lý người dùng</button>
            <button id="order-management">Quản lý đơn hàng</button>
        </section>
    </div>
</body>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("login").addEventListener("click", function () {
            window.location.href = "login"
        })

        document.getElementById("sign-up").addEventListener("click", function () {
            window.location.href = "sign-up"
        })

        document.getElementById("user-management").addEventListener("click", function () {
            window.location.href = "user-management"
        })

        document.getElementById("order-management").addEventListener("click", function () {
            window.location.href = "order-management"
        })
    })
</script>
</html>