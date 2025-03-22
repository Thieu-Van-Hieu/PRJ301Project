<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đặt lại mật khẩu thành công</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f2f2f2;
            }
            .container {
                max-width: 500px;
                margin: 100px auto;
                padding: 20px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                text-align: center;
            }
            h2 {
                color: #4CAF50;
            }
            p {
                font-size: 16px;
                color: #333;
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
            a:hover {
                background: #45a049;
            }
        </style>
    </head>
    <body>
        <jsp:include page="noticeSuccess.jsp"/>
        <jsp:include page="sakura.jsp"/>
        <div class="container">
            <h2>Đặt lại mật khẩu thành công</h2>
            <p>Mật khẩu của bạn đã được cập nhật thành công.</p>
            <a href="${pageContext.request.contextPath}/view/login.jsp">Quay lại đăng nhập</a>
        </div>
    </body>
    <jsp:include page="background.jsp" />
</html>
