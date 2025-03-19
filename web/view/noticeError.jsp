<%-- 
    Document   : noticeError
    Created on : Mar 18, 2025, 9:36:32 PM
    Author     : ngoct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông báo lỗi</title>
        <style>
            /* Container thông báo */
            .notification {
                position: fixed;
                top: 20px;
                right: -300px; /* Ẩn ngoài màn hình */
                width: 280px;
                padding: 15px;
                background-color: #ff4d4d;
                color: white;
                font-size: 18px;
                font-weight: bold;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                display: flex;
                justify-content: space-between;
                align-items: center;
                transition: right 0.5s ease-in-out;
            }

            /* Khi hiển thị, dịch sang trái */
            .show {
                right: 20px;
            }

            /* Nút đóng thông báo */
            .close-btn {
                background: none;
                border: none;
                color: white;
                font-size: 20px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <c:if test="${not empty error}">
            <div id="notification" class="notification">
                ${error}
                <button class="close-btn" onclick="closeNotification()">×</button>
            </div>
            <script>
                window.onload = function () {
                    let notification = document.getElementById("notification");
                    notification.classList.add("show");
                    setTimeout(() => {
                        closeNotification();
                    }, 3000);
                };

                function closeNotification() {
                    let notification = document.getElementById("notification");
                    notification.classList.remove("show");
                }
            </script>
        </c:if>
    </body>
</html>
