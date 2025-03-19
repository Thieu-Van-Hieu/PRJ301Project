<%-- 
    Document   : forgorPassword.jsp
    Created on : Mar 18, 2025, 10:11:20 PM
    Author     : ngoct
--%>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quên mật khẩu</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forgotpassword-css/forgot.css" />
    </head>
    <body>
        <jsp:include page="sakura.jsp" />
        <div class="container">
            <div class="form-forgot">
                <h2>Quên mật khẩu</h2>
                <form action="${pageContext.request.contextPath}/ForgotPasswordServlet" method="post">
                    <div class="form-row">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <button type="submit">Gửi yêu cầu</button>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="background.jsp" />
    <script src="${pageContext.request.contextPath}/assets/js/valiate/emailforgot.js"></script>
</html>
