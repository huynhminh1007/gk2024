<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
  </head>

  <body>
    <div id="wrapper">
      <section class="container">
        <div class="login-container">
          <h2 class="form-heading">Đăng nhập</h2>
          <form id="loginForm">
            <div class="input-group">
              <label for="email">Email</label>
              <input type="email" id="email" name="email" required>
            </div>

            <div class="input-group">
              <label for="password">Mật khẩu</label>
              <input type="password" id="password" name="password" required>
            </div>

            <button class="form-submit" type="submit">Đăng nhập</button>
          </form>
          <h2 id="form-message-response"></h2>
        </div>
      </section>
    </div>
  </body>
  <script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
  <script type="text/javascript" src="javascripts/jquery-3.7.1.js"></script>
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let email = document.getElementById("email").value;
        let password = document.getElementById("password").value;

        $.ajax({
          type: "POST",
          url: "login",
          data: {
            email: email,
            password: password
          },
          success: function (resp) {
            $("#form-message-response").text(resp.message);
          }
        })
      })
    })
  </script>
</html>