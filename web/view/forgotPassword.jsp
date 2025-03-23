<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quên mật khẩu - Nhận OTP</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forgotpassword-css/forgotpassword.css" />
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
