<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            background-color: #007BFF;
            /* Màu xanh dương */
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .account-container {
            background-color: #e0f2ff;
            width: 400px;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        .profile-header {
            display: flex;
            align-items: center;
            padding-bottom: 10px;
            border-bottom: 2px solid #bde0ff;
        }

        .profile-header img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background-color: #004a99;
            margin-right: 10px;
        }

        .profile-header h2 {
            margin: 0;
            color: #004a99;
        }

        .info-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #bde0ff;
        }

        .info-item:last-child {
            border-bottom: none;
        }

        .edit-button {
            background-color: #ccc;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .logout-button {
            width: 100%;
            background-color: #d9534f;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>

<body>
    <jsp:include page="sakura.jsp" />
    <jsp:include page="background.jsp" />

    <div class="account-container">
        <div class="profile-header">
            <img src="" alt="Avatar">
            <h2>Hùng dz</h2>
        </div>
        <div class="info-item">
            <span>Tên Hiển Thị</span>
            <span>Hùng dz</span>
        </div>
        <div class="info-item">
            <span>Mật khẩu</span>
            <span><input type="password" name="password"></span>
            <button class="edit-button">Chỉnh sửa</button>
        </div>
        <div class="info-item">
            <span>Email</span>
            <span><input type="text" name="password"></span>
            <button class="edit-button">Chỉnh sửa</button>
        </div>
        <div class="info-item">
            <span>Số điện thoại</span>
            <span><input type="text" name="password"></span>
            <button class="edit-button">Chỉnh sửa</button>
        </div>
        <button class="logout-button">Đăng xuất</button>
    </div>
</body>

</html>