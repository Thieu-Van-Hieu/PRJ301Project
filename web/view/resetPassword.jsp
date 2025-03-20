<%-- 
    Document   : reset_password
    Created on : Mar 18, 2025, 11:11:38 PM
    Author     : ngoct
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đặt lại mật khẩu</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login-css/reset_password.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f2f2f2;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 100%;
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .reset-form {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                width: 400px;
            }
            .reset-form h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input[type="password"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
            }
            .reset-form button {
                width: 100%;
                padding: 12px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            .reset-form button:hover {
                background-color: #45a049;
            }
            .error-message {
                color: red;
                text-align: center;
                margin-top: 5px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <jsp:include page="sakura.jsp" />
        <jsp:include page="noticeError.jsp"/>
        <div class="container">
            <div class="reset-form">
                <h2>Đặt lại mật khẩu</h2>
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>
                <form action="${pageContext.request.contextPath}/ResetPasswordServlet" method="post">
                    <div class="form-group">
                        <label for="newPassword">Mật khẩu mới:</label>
                        <input type="password" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Xác nhận mật khẩu:</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <input type="hidden" name="token" value="${token}">
                    <button type="submit">Đặt lại mật khẩu</button>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="background.jsp" />
    <script src="${pageContext.request.contextPath}/assets/js/valiate/passwordright.js"></script>
</html>
