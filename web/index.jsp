<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>4Club - Welcome</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta
            name="description"
            content="Welcome to 4Club, the best place to connect and share."
        />
        <%-- <c:forEach var="season" begin="0" end="3">
            <c:forEach var="time" begin="0" end="5">
                <link
                    rel="preload"
                    href="${pageContext.request.contextPath}/assets/img/background/${season}_${time}.png"
                    as="image"
                />
            </c:forEach>
        </c:forEach> --%>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap"
            rel="stylesheet"
        />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/grid.css" />
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
                background: url('${pageContext.request.contextPath}/assets/img/background/0_0.png') no-repeat center center;
                transition: background-image 2s ease-in-out;
            }

            #preloadBackground {
                display: none;
                background: url('${pageContext.request.contextPath}/assets/img/background/0_1.png') no-repeat center center;
            }

            .app {
                height: 100vh;
                width: 100%;
            }

            .app__heading h1 {
                font-family: "Oi", sans-serif;
                font-size: 16rem;
                color: #fff;
                text-shadow: 0 0 1rem rgba(0, 0, 0, 0.5);
                font-weight: 400;
                text-align: center;
            }

            .app__btn {
                margin-top: 4rem;
            }

            .btn {
                font-size: 5.2rem;
                line-height: 1.2;
                color: #fff;
                background: rgba(255, 255, 255, 0.2);
                border-radius: 1rem;
                cursor: pointer;
                text-align: center;
                transition: all 0.3s ease;
            }

            .btn:hover {
                background: rgba(255, 255, 255, 0.5);
                transform: scale(1.1);
            }

            .btn a {
                display: block;
                width: 100%;
                padding: 2rem 3.6rem;
                text-decoration: none;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <div class="app">
            <div class="grid wide">
                <div class="app__heading row">
                    <h1 class="col l-12">4Club</h1>
                </div>
                <div class="app__btn row">
                    <div class="btn col l-3 l-o-2">
                        <a href="${pageContext.request.contextPath}/view/signup.jsp">Sign Up</a>
                    </div>
                    <div class="btn col l-3 l-o-1">
                        <a href="${pageContext.request.contextPath}/view/login.jsp">Log In</a>
                    </div>
                </div>
            </div>
        </div>

        <div id="background"></div>
        <div id="preload-background"></div>
        <script>
            function move(element) {
                var x =
                    Math.random() * (window.innerWidth - element.clientWidth);
                var y =
                    Math.random() * (window.innerHeight - element.clientHeight);
                element.style.position = "absolute";
                element.style.left = x + "px";
                element.style.top = y + "px";
            }

            let index = 0;

            function updateBackground() {
                index = (index + 1) % 24;
                let background = document.getElementById("background");
                let preloadBackground = document.getElementById("preload-background");

                let backgroundUrl = "url('${pageContext.request.contextPath}/assets/img/background/" + (Math.floor(index / 6)) + "_" + (index % 6) + ".png')";
                let preloadBackgroundUrl = "url('${pageContext.request.contextPath}/assets/img/background/" + (Math.floor((index + 1) / 6)) + "_" + ((index + 1) % 6) + ".png')";
                background.style.backgroundImage = backgroundUrl;
                preloadBackground.style.backgroundImage = preloadBackgroundUrl;
            }

            setInterval(updateBackground, 3000);
        </script>
    </body>
</html>
