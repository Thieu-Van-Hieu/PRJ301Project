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
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html {
            font-family: Arial, sans-serif;

        }

        body {
            background-color: #007BFF;
            /* Màu xanh dương */
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: right;
            align-items: end;
            height: 100vh;
            margin: 0;
        }

        .account-container {
            display: flex;
            flex-direction: column;
            justify-content: end;
            background-color: #eaf8f2;
            width: 92vw;
            height: 92vh;
            border-top-left-radius: 12px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .account-body {
            width: 100%;
            height: 85%;
            background-color: #b2d8d8;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: end;
        }

        .account-avatar {
            width: 120px;
            height: 120px;
            position: absolute;
            top: -60px;
            left: 60px;
            border: 8px solid #b2d8d8;
            border-radius: 50%;
        }

        .account-avatar img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }

        .account-body .account-name {
            position: absolute;
            color: #007BFF;
            top: 10px;
            left: 188px;
            font-size: 24px;
        }

        .information-container {
            background-color: #d9d9d9;
            width: 96%;
            height: 90%;
            border-radius: 12px;
            padding: 24px;
            display: flex;
            flex-direction: column;
        }

        .information-item {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #ccc;
            padding: 4px 10px 12px 10px;
        }

        .information-container .infor {
            display: flex;
            flex-direction: column;
            padding: 5px;
            color: #007BFF;
            gap: 5px;
        }

        .btn-logout,
        .btn-setting {
            border: none;
            background-color: #737373;
            color: #fff;
            padding: 12px 24px;
            border-radius: 16px;
            font-size: 12px;
            transition: 0.3s ease-in-out;
            cursor: pointer;
        }
        .btn-logout:hover,
        .btn-setting:hover {
            border: 2px solid orangered;
            background-color: #fff;
            color: orangered;
        }

        .btn-exit {
            width: 42px;
            height: 42px;
            border: 1px solid #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 50%;
            position: absolute;
            z-index: 1000;
            top: 24px;
            right: 24px;
            color: #ccc;
            cursor: pointer;
        }

        .btn-logout {
            width: 120px;
            text-align: center;
            align-self: end;
            margin-top: 24px;
        }
    </style>
</head>

<body>
    <jsp:include page="sakura.jsp" />
    <jsp:include page="background.jsp" />


    <div class="account-container">
        <div class="btn-exit" onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?userId=${userInfor.userId}'">Esc</div>
        <div class="account-body">
            <div class="account-avatar">
                <img src="${pageContext.request.contextPath}/assets/img/img-download/${userInformation.avatar}" alt="">
            </div>
            <h3 class="account-name">${userInfor.lastName} ${userInfor.firstName}</h3>
            <div class="information-container">
                <div class="information-item">
                    <div class="infor">
                        <h4>Tài Khoản: </h4>
                        <h5>${userInfor.userName}</h5>
                    </div>
                    <div class="nothing"></div>
                </div>
                <div class="information-item">
                    <div class="infor">
                        <h4>Mật Khẩu: </h4>
                        <h5>****************</h5>
                    </div>
                    <button class="btn-setting">Chỉnh sửa</button>
                </div>
                <div class="information-item">
                    <div class="infor">
                        <h4>Email: </h4>
                        <h5>${userInfor.email}</h5>
                    </div>
                    <button class="btn-setting">Chỉnh sửa</button>
                </div>
                <div class="btn-logout" onclick="location.href = '${pageContext.request.contextPath}/UserSettingServlet?action=logout'">Đăng Xuất</div>
                
            </div>

        </div>
    </div>


</body>

</html>