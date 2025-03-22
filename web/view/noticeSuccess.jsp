<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty success}">
    <style>
        .notification.success {
            position: fixed;
            top: 20px;
            right: -300px;
            width: 280px;
            padding: 15px;
            background-color: #4CAF50;
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
        .notification.success.show {
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

    <div id="notification-success" class="notification success">
        ${success}
        <button class="close-btn" onclick="closeNotificationSuccess()">×</button>
    </div>
    <script>
        window.addEventListener("load", function () {
            var notification = document.getElementById("notification-success");
            if (notification) {
                notification.classList.add("show");
                setTimeout(closeNotificationSuccess, 3000);
            }
        });
        function closeNotificationSuccess() {
            var notification = document.getElementById("notification-success");
            if (notification) {
                notification.classList.remove("show");
            }
        }
    </script>
    <% session.removeAttribute("success"); %>
</c:if>
