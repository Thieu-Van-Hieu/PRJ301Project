<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ÄÄng kÃ½</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login-css/register.css" />
</head>

<body>
    <jsp:include page="sakura.jsp" />
    <div class="container">
        <div class="form-register">
            <h2>Đăng ký tài khoản</h2>
            <form action="information.jsp">
                <div class="form-row">
                    <label for="username">Tài khoản:</label>
                    <input type="text" id="username" name="username" autocomplete="off" required>
                </div>

                <div class="form-row">
                    <label for="matKhau">Mật khẩu:</label>
                    <input type="password" id="matKhau" name="matKhau" required>
                </div>

                <div class="form-row">
                    <label for="nhapLaiMatKhau">Nhập lại mật khẩu:</label>
                    <input type="password" id="nhapLaiMatKhau" name="nhapLaiMatKhau" required
                        onchange="checkPasswordMatch()">
                    <div id="error-message" style="color: red; display: none; padding-bottom: 10px; font-weight: 600;">
                        Mật khẩu và Nhập lại mật khẩu không khớp!</div>
                </div>

                <div class="form-row">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <button type="submit" style="color: white; font-weight: 700;">Đăng ký</button>
            </form>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath}/assets/js/valiate/passwordright.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/valiate/signupform.js"></script>

</html>