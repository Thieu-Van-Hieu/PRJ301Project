<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>OTP đã được gửi</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f2f2f2;
            }
            .container {
                max-width: 400px;
                margin: 100px auto;
                padding: 20px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h2 {
                text-align: center;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            input {
                margin-bottom: 10px;
                padding: 8px;
                font-size: 16px;
            }
            button {
                padding: 10px;
                font-size: 16px;
                background: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
            }
            .error {
                color: red;
                text-align: center;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="noticeSuccess.jsp"/>
        <jsp:include page="noticeError.jsp"/>
        <jsp:include page="sakura.jsp" />
        <div class="container">
            <h2>Nhập OTP</h2>
            <form action="${pageContext.request.contextPath}/VerifyOTPServlet" method="post">
                <label for="otp">OTP:</label>
                <input type="text" name="otp" id="otp" required placeholder="Nhập mã OTP">
                <button type="submit">Xác thực OTP</button>
            </form>
            <p style="text-align: center;">
                Nếu không nhận được OTP, 
                <a href="${pageContext.request.contextPath}/ForgotPasswordServlet?email=${param.email}" style="text-decoration: none;color: #3D2097; font-weight: 600;">Gửi lại</a>.
            </p>
        </div>
    </body>
    <jsp:include page="background.jsp" />
</html>
