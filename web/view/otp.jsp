<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Xác nhận OTP - 4Club</title>
        <jsp:include page="sakura.jsp" />
       <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/otp-css/otp.css" />
    </head>
    <body>
        <div class="container">
            <h2>Xác nhận OTP</h2>
            <p>Vui lòng nhập mã OTP bạn nhận được qua email:</p>
            <% String error = (String) request.getAttribute("errorOTP"); %>
            <% if (error != null) { %>
            <div class="error"><%= error %></div>
            <% } %>
            <% String message = (String) request.getAttribute("message"); %>
            <% if (message != null) { %>
            <div class="message"><%= message %></div>
            <% } %>
            <form action="view/information.jsp" method="post">
                <label for="otp">Mã OTP:</label>
                <input type="text" name="otp" id="otp" required>
                <input type="submit" value="Xác nhận">
            </form>
            <div class="resend">
                <form action="ResendOTPServlet" method="post">
                    <input type="submit" value="Gửi lại OTP">
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="background.jsp" />
</html>
