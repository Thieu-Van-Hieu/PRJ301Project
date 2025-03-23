<%-- 
    Document   : changepassword
    Created on : Mar 23, 2025, 1:25:10 PM
    Author     : ngoct
--%>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Đổi Mật Khẩu</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login-css/changepassword.css" />
    </head>
    <body>
        <jsp:include page="noticeError.jsp"/>
        <jsp:include page="noticeSuccess.jsp"/>
        <jsp:include page="sakura.jsp" />
        <div class="change-password-container">
            <h2>Đổi Mật Khẩu</h2>
            <form action="${pageContext.request.contextPath}/change" method="post">
                <input type="hidden" name="action" value="changePassword" />
                <div>
                    <label for="oldPassword">Mật khẩu cũ:</label>
                    <input type="password" id="oldPassword" name="oldPassword" required />
                </div>
                <div>
                    <label for="newPassword">Mật khẩu mới:</label>
                    <input type="password" id="newPassword" name="newPassword" required />
                </div>
                <div>
                    <label for="confirmPassword">Xác nhận mật khẩu mới:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required />
                </div>
                <button type="submit">Đổi mật khẩu</button>
            </form>
            <a href="${pageContext.request.contextPath}/UserSettingServlet?action=account" class="back-link">Quay lại</a>
        </div>
    </body>
    <jsp:include page="background.jsp" />
    <script src="${pageContext.request.contextPath}/assets/js/valiate/changepassword.js"></script>
</html>
