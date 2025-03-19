<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quên mật khẩu - Nhận OTP</title>
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
                font-weight: 700;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            label, input {
                font-weight: 700;
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
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            button:hover {
                background-color: #45a049;
            }
            .error {
                color: red;
                font-size: 14px;
                margin-bottom: 10px;
                display: none;
            }
        </style>
    </head>
    <jsp:include page="noticeError.jsp"/>
    <jsp:include page="sakura.jsp" />
    <body>
        <div class="container">
            <h2>Quên mật khẩu</h2>
            <div id="emailError" class="error">Email chỉ được dùng @fpt.edu.vn hoặc @gmail.com.</div>
            <form action="${pageContext.request.contextPath}/ForgotPasswordServlet" method="post" id="forgotForm">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required placeholder="Nhập email của bạn">
                <button type="submit">Gửi OTP</button>
            </form>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/assets/js/valiate/validateEmail.js"></script>
    <jsp:include page="background.jsp" />
</html>
