<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>4Club - Câu Lạc Bộ</title>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
              integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>

        <jsp:include page="sakura.jsp" />
        <jsp:include page="background.jsp" />
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
                width: 25%;
                height: calc(100vh - 60px);
                grid-area: sidebar;
                background-color: #b2d8d8;
                border-top-left-radius: 10px;
            }

            .sidebar .sidebar-header {
                color: #fff;
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 12px;
                padding: 20px 5px;
            }

            .sidebar-header__item {
                color: #fff;
                background-color: #064273;
                padding: 5px;
                border-radius: 50%;
            }

            .sidebar-header__name {
                font-size: 25px;
                font-weight: 600;
            }

            .sidebar-coverImg {
                width: 100%;
            }

            .sidebar-coverImg img {
                width: 100%;
            }


            .sidebar ul {
                list-style: none;
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .sidebar ul .hidden {
                max-height: 0;
                display: none;
                transition: max-height 2s ease-in-out;
            }

            .sidebar ul .show {
                display: flex;
                max-height: 300px;
                margin-left: 16px;
            }

            .sidebar__item a, .sidebar__item label {
                text-decoration: none;
                color: #064273;
                font-size: 16px;
                display: block;
                padding: 12px 0;
                padding-left: 20px;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .sidebar__item a:hover, .sidebar__item label:hover {
                border-left: 2px solid #064273;
                color: white;
                padding-left: 18px;
            }

            /* Nội dung chính */
            .content {
                display: flex;
                flex-direction: column;
                grid-area: cont;
                overflow-y: auto;
                height: 100%;
            }

            #task-list {
                display: none;
                padding-left: 20px;
            }

            #task__toggle:checked~#task-list {
                display: block;
            }
        </style>
        <jsp:include page="/view/background.jsp" />
        <div class="container">
            <!-- Header -->
            <div class="header">
                <div class="header-title">
                    <i class="fa-regular fa-compass"></i>
                    <span>${clubName}</span>
                </div>
                <div class="header-icons">
                    <a href="#"><i class="fa-solid fa-envelope"></i></a>
                    <a href="#">?</a>
                </div>
            </div>

            <!-- Thanh cÃ´ng cá»¥ bÃªn trÃ¡i -->
            <div class="left-toolbar">
                <ul>
                    <li><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt=""></li>
                    <c:forEach var="clubItem" items="${clubListItems}">
                        <li id="${clubItem.id}" onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?action=open&clubId=${clubItem.id}'">
                            <img src="${pageContext.request.contextPath}/assets/img/img-download/${clubItem.name}" alt="alt"/>
                        </li>
                    </c:forEach>
                    <li><i class="fa-solid fa-plus"></i></li>
                    <li><i class="fa-regular fa-compass"></i></li>
                    <li><i class="fa-solid fa-gear"></i></li>
                </ul>
            </div>

            <!-- Sidebar -->
            <div class="sidebar">
                <div class="sidebar-header">
                    <div class="sidebar-header__item"><i class="fa-solid fa-house"></i></div>
                    <div class="sidebar-header__name">${clubName}</div>
                </div>
                <div class="sidebar-coverImg">
                    <img src="${pageContext.request.contextPath}/assets/img/login-img/loginnight.jpg" alt="">
                </div>
                <ul class="sidebar__menu">
                    <li class="sidebar__item"><a href="${pageContext.request.contextPath}/ForumServlet">Diễn đàn</a></li>
                    <li class="sidebar__item"><a href="${pageContext.request.contextPath}/CalendarServlet">Lịch</a></li>
                    <li class="sidebar__item"><a href="${pageContext.request.contextPath}/EventServlet">Sự Kiện</a></li>
                    <li class="sidebar__item"><a href="${pageContext.request.contextPath}/">Đóng tiền</a></li>
                    <li class="sidebar__item">
                        <input type="checkbox" id="task__toggle" style="display: none;">
                        <label for="task__toggle">Nhiệm vụ</label>
                        <ul id="task-list">
                            <li><a href="${pageContext.request.contextPath}/TaskAssignedToMeServlet">Nhiệm vụ được
                                    giao</a></li>
                            <li><a href="${pageContext.request.contextPath}/TaskAssignedByMeServlet">Giao nhiệm vụ</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar__item"><a href="${pageContext.request.contextPath}/">Báo Cáo</a></li>
                    <li class="sidebar__item"><a href="${pageContext.request.contextPath}/MessengerServlet">Nhóm của bạn</a></li>
                </ul>
            </div>

            <div class="content">
                <c:set var="pageToInclude" value="${empty includeWeb ? 'forum.jsp' : includeWeb}" />
                <jsp:include page="${pageToInclude}" />
            </div>
    </body>

</html>