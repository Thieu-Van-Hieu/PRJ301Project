<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OTP Xác thực thành công</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f2f2f2; }
        .container {
            width: 100%;
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #4CAF50;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>OTP xác thực thành công</h2>
        <p>Bạn đã xác thực OTP thành công. Bây giờ, bạn có thể đặt lại mật khẩu của mình.</p>
        <a href="${pageContext.request.contextPath}/reset_password.jsp">Đặt lại mật khẩu</a>
    </div>
</body>
</html>
