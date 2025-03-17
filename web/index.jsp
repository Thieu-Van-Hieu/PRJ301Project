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
        <link rel="stylesheet" href="assets/css/hoaroi.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/grid.css" />
        <style>
            .petal {
                position: absolute;
                width: 20px;
                height: 20px;
                background: url('assets/img/effect-img/hoaanhdao.png') no-repeat center center;
                background-size: contain;
                opacity: 0.9;
                animation: fall linear infinite;
            }
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
                position: relative;
                height: 100vh;
                width: 100%;
                display: grid;
                grid-template-rows: 1fr 1fr 3fr;
                justify-content: center;
                align-items: center;
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
                display: flex;
                justify-content: space-around;
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

            /* Responsive cho màn hình nhỏ hơn 768px (điện thoại) */
            @media (max-width: 768px) {
                .app__heading h1 {
                    font-size: 8rem; /* Giảm kích thước tiêu đề */
                }

                .app__btn {
                    flex-direction: column; /* Sắp xếp các nút theo cột */
                    align-items: center;
                    gap: 1rem;
                }

                .btn {
                    font-size: 4rem; /* Giảm kích thước chữ trong nút */
                    width: 80%; /* Đặt chiều rộng nút phù hợp với màn hình nhỏ */
                }

                .btn a {
                    padding: 1.5rem 2rem; /* Giảm padding trong nút */
                }
            }

            /* Responsive cho màn hình nhỏ hơn 1024px (máy tính bảng) */
            @media (max-width: 1024px) {
                .app__heading h1 {
                    font-size: 12rem; /* Điều chỉnh kích thước tiêu đề */
                }

                .btn {
                    font-size: 4.5rem; /* Điều chỉnh kích thước chữ trong nút */
                }

                .btn a {
                    padding: 1.8rem 2.8rem; /* Điều chỉnh padding trong nút */
                }
            }
        </style>
    </head>
    <body>
          <div id="petalContainer"></div>
        <div class="app">
            <div class="app__heading">
                <h1>4Club</h1>
            </div>
            <div class="app__btn">
                <div class="btn" random-position="mouseover">
                    <a href="${pageContext.request.contextPath}/view/register.jsp">Sign Up</a>
                </div>
                <div class="btn" random-position="mouseover">
                    <a href="${pageContext.request.contextPath}/view/login.jsp">Log In</a>
                </div>
            </div>
        </div>

        <div id="background"></div>
        <div id="preload-background"></div>
        <script>

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
        <script src="${pageContext.request.contextPath}/assets/js/troll/randomPosition.js"></script>
    </body>
    <script src="assets/js/effect-js/roihoa.js"></script>
</html>
