<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.Calendar"%>
<%
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY); // Giờ bắt đầu từ 0 (0:00) đến 23 (23:00)
    int month = calendar.get(Calendar.MONTH); // Tháng bắt đầu từ 0 (January) đến 11 (December)
    String __url = request.getContextPath() + "/assets/img/background/" + (month / 3) + "_" + (hour / 4) + ".png";
    pageContext.setAttribute("url", __url);
%>
<head>
    <style>
        #background {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: -1;
            width: 100%;
            height: 100%;
            background: url('${url}') no-repeat center center;
            transition: background-image 5s ease-in-out;
        }
    </style>
</head>
<body>
    <div id="background">
    </div>
    <script>
        function updateBackground() {
            let now = new Date();
            let hour = now.getHours();
            let  month = now.getMonth();
            let background = document.getElementById("background");
            let backgroundUrl = "url('${pageContext.request.contextPath}/assets/img/background/" + (Math.floor(month / 3)) + "_" + (Math.floor(hour / 4)) + ".png')";
            
            if (background.style.background !== backgroundUrl) {
                background.style.backgroundImage = backgroundUrl;
            }
        }

        setInterval(updateBackground, 1000);
    </script>
</body>
