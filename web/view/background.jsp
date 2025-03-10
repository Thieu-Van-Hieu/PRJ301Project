<%@page contentType="text/html;charset=UTF-8" language="java"%>
<head>
    <style>
        .background {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: -1;
            width: 100%;
            height: 100%;
            background: url("${pageContext.request.contextPath}/assets/img/background_full.gif") no-repeat center center;
        }
    </style>
</head>
<body>
    <div class="background">
    </div>
    <script>
        function updateGreeting() {
            let now = new Date();
            let hour = now.getHours();
            let greetingText = "";

            if (hour >= 5 && hour < 12) {
                greetingText = "Good Morning";
            } else if (hour >= 12 && hour < 18) {
                greetingText = "Good Afternoon";
            } else {
                greetingText = "Good Night";
            }

            document.getElementById("greeting").innerText = greetingText;
        }

        updateGreeting();

        function updateBackground() {
            let now = new Date();
            let hour = now.getHours();
            let container = document.querySelector(".container");

            if (hour >= 5 && hour < 12) {
                container.style.backgroundImage =
                    "url('${pageContext.request.contextPath}/assets/img/background_morning.jpg')";
            } else if (hour >= 12 && hour < 18) {
                container.style.backgroundImage =
                    "url('${pageContext.request.contextPath}/assets/img/background_noon.jpg')";
            } else {
                container.style.backgroundImage =
                    "url('${pageContext.request.contextPath}/assets/img/background_night.jpg')";
            }
            container.style.backgroundSize = "cover";
            container.style.backgroundPosition = "center";
            container.style.backgroundRepeat = "no-repeat";
            container.style.transition = "background 0.5s ease-in-out";
        }

        updateBackground();
    </script>
</body>
