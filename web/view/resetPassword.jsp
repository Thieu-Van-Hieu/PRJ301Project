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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login-css/resetPassword.css">
    </head>
    <body>
        <jsp:include page="sakura.jsp" />
        <jsp:include page="noticeError.jsp"/>
        <jsp:include page="noticeSuccess.jsp"/>
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
                        <div id="newPasswordError" style="display: none; color: red; margin-top: 5px; font-weight: 700;"></div>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Xác nhận mật khẩu:</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                        <div id="passwordError" style="display: none; color: red; margin-top: 5px; font-weight: 700;">
                            Mật khẩu không khớp
                        </div>
                    </div>
                    <input type="hidden" name="token" value="${token}">
                    <button type="submit">Đặt lại mật khẩu</button>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="background.jsp" />
    <script src="${pageContext.request.contextPath}/assets/js/valiate/setupforgotpassword.js"></script>
</html>
