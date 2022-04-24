<%-- 
    Document   : login
    Created on : Feb 27, 2022, 10:32:29 AM
    Author     : firem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- === Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="css/login.css">

        <title>Login & Registration Form</title>
    </head>
    <body>

        <div class="container">
            <div class="forms">
                <div class="form login">
                    <span class="title">Login</span>

                    <form action="login" method="POST">
                        <div class="input-field">
                            <input type="text" name="user" placeholder="Enter your name" required>
                            <i class="uil uil-user"></i>
                        </div>
                        <div class="input-field">
                            <input type="password" name="pass" class="password" placeholder="Enter your password" required>
                            <i class="uil uil-lock icon"></i>
                            <i class="uil uil-eye-slash showHidePw"></i>
                        </div>
                        <c:if test="${requestScope.errorMessage != null}">
                            <div class="error-login">
                                <p>${requestScope.errorMessage}</p>
                            </div>
                        </c:if>
                        <div class="input-field button">
                            <input type="submit" value="Login Now">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            const container = document.querySelector(".container"),
                    pwShowHide = document.querySelectorAll(".showHidePw"),
                    pwFields = document.querySelectorAll(".password"),
                    signUp = document.querySelector(".signup-link"),
                    login = document.querySelector(".login-link");

            //   js code to show/hide password and change icon
            pwShowHide.forEach(eyeIcon => {
                eyeIcon.addEventListener("click", () => {
                    pwFields.forEach(pwField => {
                        if (pwField.type === "password") {
                            pwField.type = "text";

                            pwShowHide.forEach(icon => {
                                icon.classList.replace("uil-eye-slash", "uil-eye");
                            });
                        } else {
                            pwField.type = "password";

                            pwShowHide.forEach(icon => {
                                icon.classList.replace("uil-eye", "uil-eye-slash");
                            });
                        }
                    });
                });
            });

        </script>

    </body>
</html>

