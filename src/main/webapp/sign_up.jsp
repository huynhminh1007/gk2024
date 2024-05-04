<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Đăng ký</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
    <div id="wrapper">
        <section class="container">
            <div class="sign-up-container">
                <h2 class="form-heading">Đăng ký</h2>
                <form id="signUpForm">
                    <div class="input-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                    </div>

                    <div class="input-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                    <div class="input-group">
                        <label for="fullName">Họ và tên</label>
                        <input type="text" id="fullName" name="fullName" required>
                    </div>

                    <div class="input-group">
                        <label>Giới tính</label>
                        <div class="radio-group">
                            <input type="radio" id="male" name="gender" value="male" required>
                            <label for="male">Nam</label>

                            <input type="radio" id="female" name="gender" value="female" required>
                            <label for="female">Nữ</label>
                        </div>
                    </div>

                    <button class="form-submit" type="submit">Đăng ký</button>
                </form>
                <h2 id="form-message-response"></h2>
            </div>
        </section>
    </div>
</body>
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script>
    $(document).ready(function () {
        $('#signUpForm').submit(function (event) {
            event.preventDefault()

            let formData = {
                email: $('#email').val(),
                password: $('#password').val(),
                fullName: $('#fullName').val(),
                gender: $('input[name="gender"]:checked').val()
            }

            $.ajax({
                type: "POST",
                url: "sign-up",
                data: formData,
                success: function (resp) {
                    $("#form-message-response").text(resp.message);
                }
            })
        })
    })
</script>
</html>
