<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Login Page</title>
        <!-- Link đến file CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/responsivelogin.css" />
    </head>
    <body>
        <div class="container">
            <div class="left-intro">
                <h1 id="greeting"></h1>
                <p>Have a great journey ahead!</p>
            </div>
            <div class="login-form">
                <h2>Sign In</h2>
                <form action="#" method="post">
                    <div class="form-group">
                        <label for="username">User name</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            placeholder="Enter your username"
                        />
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            placeholder="Enter your password"
                        />
                    </div>

                    <div class="options">
                        <label>
                            <input type="checkbox" name="remember" />
                            Remember me
                        </label>
                        <a href="#">Forgot Password?</a>
                    </div>

                    <button type="submit" class="btn-submit">Sign In</button>

                    <div class="signup-link">
                        <p>Don't have an account? <a href="#">Sign up</a></p>
                    </div>
                </form>
            </div>
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
</html>
