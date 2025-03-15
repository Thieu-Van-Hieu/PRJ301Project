<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4Club - Câu Lạc Bộ</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="../assets/css/discovery.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html,
        body {
            font-family: Arial, sans-serif;
            height: 100%;
        }

        .container {
            display: grid;
            width: 100vw;
            height: 100vh;
            grid-template-columns: 1fr 3fr 8fr;
            /* Left-toolbar (1) - Sidebar (3) - Content (8) */
            grid-template-rows: 60px 1fr;
            /* Header chiếm 1 hàng, phần còn lại chiếm toàn bộ */
            grid-template-areas:
                "header header header"
                "left-toolbar sidebar cont";
            overflow: hidden;
        }


        /* Header */
        .header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 60px;
            z-index: 1000;
            grid-area: header;
            display: flex;
            justify-content: right;
            gap: 40%;
            align-items: center;
            color: white;
            padding: 10px 20px;
        }

        .header-title {
            display: flex;
            align-items: center;
            font-size: 18px;
        }

        .header-title i {
            margin-right: 10px;
        }

        .header-icons {
            display: flex;
            gap: 20px;
        }

        .header-icons a {
            color: white;
            font-size: 18px;
            text-decoration: none;
        }

        /* Thanh công cụ tròn bên trái */
        .left-toolbar {
            position: fixed;
            top: 60px;
            left: 0;
            width: 8.3%;
            height: calc(100vh - 60px);
            grid-area: left-toolbar;
            align-items: center;
            padding: 10px;
        }

        .left-toolbar ul {
            list-style: none;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }

        .left-toolbar li {
            width: 40px;
            height: 40px;
            background: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
        }

        .left-toolbar li img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
        }

        /* Sidebar */
        .sidebar {
            position: fixed;
            top: 60px;
            left: 8.4%;
            width: 25%;
            height: calc(100vh - 60px);
            grid-area: sidebar;
            background: #b2d8d8;
            border-top-left-radius: 10px;

        }

        .sidebar h2 {
            font-size: 20px;
            margin-bottom: 15px;
            color: #064273;
            margin-top: 10px;
            padding-left: 20px;
        }

        .sidebar ul {
            list-style: none;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .sidebar ul li {}

        .sidebar ul li a {
            text-decoration: none;
            color: #064273;
            font-size: 16px;
            display: block;
            padding: 12px 0;
            padding-left: 20px;
            transition: all 0.3s ease;
        }

        .sidebar ul li a:hover {
            border-left: 2px solid #064273;
            color: white;
            padding-left: 18px;
        }

        /* Nội dung chính */
        .content {
            grid-area: cont;
            background: #eaf8f2;
            overflow-y: auto;

            height: calc(100vh - 60px);
        }

        /* Header của content (chiếm full width) */
        .content-header {
            background: #4a6fa5;
            /* Màu nền header content */
            color: white;
            padding: 40px 30px;
            text-align: left;
            width: 100%;
            /* Chiếm toàn bộ chiều rộng */
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .content-header h1 {
            width: 60%;
            font-size: 30px;
            font-family: "Oi", sans-serif;
            font-weight: 400;
        }

        .content-header p {
            font-size: 20px;
        }

        
    </style>
    <jsp:include page="/view/background.jsp" />
    <div class="container">
        <!-- Header -->
        <div class="header">
            <div class="header-title">
                <i class="fa-regular fa-compass"></i>
                <span>Nhóm 1</span>
            </div>
            <div class="header-icons">
                <a href="#"><i class="fa-solid fa-magnifying-glass"></i></a>
                <a href="#"><i class="fa-solid fa-envelope"></i></a>
                <a href="#">?</a>
            </div>
        </div>

        <!-- Thanh cÃ´ng cá»¥ bÃªn trÃ¡i -->
        <div class="left-toolbar">
            <ul>
                <li><img src="../assets/img/logo_3.jpg" alt=""></li>
                <li>1</li>
                <li><i class="fa-solid fa-plus"></i></li>
                <li><i class="fa-regular fa-compass"></i></li>
                <li><i class="fa-solid fa-gear"></i></li>
            </ul>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
            <div class="sidebar-header" id="sidebar-header">
                <i class="fa-solid fa-house"></i>
                <p>Câu lạc bộ A</p>
            </div>
            <ul>
                <li><a href="#">Diễn đàn</a></li>
                <li><a href="#">Lịch</a></li>
                <li><a href="#">Sự Kiện</a></li>
                <li><a href="#">Đóng tiền</a></li>
                <li> Nhiệm vụ
                    <ul>
                        <li>Nhiệm vụ được giao</li>
                        <li>Giao nhiệm vụ</li>
                    </ul>
                </li>
                <li><a href="#">Báo Cáo</a></li>
                <li><a href="#">Nhóm của bạn</a></li>
            </ul>
        </div>

        <!-- Ná»i dung chÃ­nh -->
        <div class="content">
            <header class="content-header">
                <div class="content-header__name">Diễn đàn</div>
                <div class="content-header__utils">
                    <i class="fa-solid fa-bullhorn"></i>
                    <i class="fa-solid fa-user-group"></i>
                    <input type="text">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
            </header>
            <div class="content-create">

            </div>


        </div>
    </div>
</body>
</html>