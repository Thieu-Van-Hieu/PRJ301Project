<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>OTP Xác thực thành công</title>
    </head>
    <body>
        <div class="container">
            <h2>OTP xác thực thành công</h2>
            <p>Bạn đã xác thực OTP thành công. Bây giờ, bạn có thể đặt lại mật khẩu của mình.</p>
            <a href="${pageContext.request.contextPath}/reset_password.jsp">Đặt lại mật khẩu</a>
        </div>
    </body>
</html>
