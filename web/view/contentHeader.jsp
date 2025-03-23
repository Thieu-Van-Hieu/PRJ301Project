<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Messenger Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .content-header {
                display: flex;
                padding: 23px 24px;
                border-bottom: 1px solid #ccc;
                background-color:#eaf8f2;
                color: #064273;
                justify-content: space-between;
            }

            .content-header_util {
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 12px;
                font-size: 24px;
                position: relative;
            }

            .content-header_util input {
                padding: 6px;
                border-radius: 12px;
                border: 1px solid;
            }

            .content-header_util .content-search {
                position: absolute;
                right: 10px;
                font-size: 16px;
                color: #CCC
            }
        </style>
    </head>
    <body>
        <header class="content-header">
            <h2>${contentHeader}</h2>
            <div class="content-header_util">
            <i class="fa-solid fa-scroll" onclick="location.href='${pageContext.request.contextPath}/RequestClubServlet'"></i>
            <i class="fa-solid fa-user-group" onclick="location.href='${pageContext.request.contextPath}/MemberServlet'"></i>
            </div>
        </header>
        <div id="scrollBox" style="display: none; position: absolute; top: 60px; right: 20px; background-color: white; border: 1px solid #ccc; padding: 10px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
            <p>This is the content inside the box.</p>
        </div>
        <script>
            function toggleBox() {
            const box = document.getElementById('scrollBox');
            box.style.display = box.style.display === 'none' ? 'block' : 'none';
            }
        </script>
    </body>
</html>
