<%-- 
    Document   : forgot_success
    Created on : Mar 18, 2025, 10:30:58 PM
    Author     : ngoct
--%>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Email đã gửi thành công</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login-css/forgot_success.css" />
        <style>
            /* Nếu chưa có file CSS riêng, bạn có thể dùng CSS inline như dưới đây */
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .message-box {
                background-color: #fff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                text-align: center;
                max-width: 400px;
            }
            .message-box h2 {
                color: #4CAF50;
                margin-bottom: 20px;
            }
            .message-box p {
                font-size: 16px;
                color: #333;
                margin-bottom: 20px;
            }
            .message-box a {
                display: inline-block;
                text-decoration: none;
                color: #fff;
                background-color: #4CAF50;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .message-box a:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="message-box">
                <h2>Thành công!</h2>
                <p>Email đặt lại mật khẩu đã được gửi thành công.</p>
                <p>Vui lòng kiểm tra email của bạn và làm theo hướng dẫn để đặt lại mật khẩu.</p>
                <a href="${pageContext.request.contextPath}/login.jsp">Quay lại đăng nhập</a>
            </div>
        </div>
    </body>
</html>
