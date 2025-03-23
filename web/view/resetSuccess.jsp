<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đặt lại mật khẩu thành công</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/otp-css/resetSuccess.css" />
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
