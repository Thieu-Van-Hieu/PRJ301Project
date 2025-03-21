<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty error}">
    <style>
        .notification.error {
            position: fixed;
            top: 70px;
            right: -300px;
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
            z-index: 9999;
            border-left: 5px solid #ffffff;
        }
        .notification.error.show {
            right: 20px;
        }
        .close-btn {
            background: none;
            border: none;
            color: white;
            font-size: 20px;
            cursor: pointer;
        }
    </style>

    <div id="notification-error" class="notification error">
        ${error}
        <button class="close-btn" onclick="closeNotificationError()">×</button>
    </div>
    <script>
        window.addEventListener("load", function () {
            var notification = document.getElementById("notification-error");
            if (notification) {
                notification.classList.add("show");
                setTimeout(closeNotificationError, 3000);
            }
        });
        function closeNotificationError() {
            var notification = document.getElementById("notification-error");
            if (notification) {
                notification.classList.remove("show");
            }
        }
    </script>
    <% session.removeAttribute("error"); %>
</c:if>
