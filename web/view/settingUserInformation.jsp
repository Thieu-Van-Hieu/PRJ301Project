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
            justify-content: right;
            align-items: end;
            height: 100vh;
            margin: 0;
        }

        .account-container {
            background-color: #e0f2ff;
            width: 92vw;
            height: 88vh;
            border-top-left-radius: 12px;
            border-bottom-left-radius: 12px;
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
        .edit-button {
            background-color: #ccc;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .logout-button {
            width: 60px;
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
         
        <button class="logout-button">Đăng xuất</button>     
    </div>
    
</body>

</html>