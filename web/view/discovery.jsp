<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4Club - </title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
    href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap"
    rel="stylesheet"
    />
    <link rel="stylesheet" href="../assets/css/discovery.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <jsp:include page="/view/background.jsp"/>
    <div class="container">
        <!-- Header -->
        <div class="header">
            <div class="header-title">
                <i class="fa-regular fa-compass"></i>
                <span>Khám Phá</span>
            </div>
            <div class="header-icons">
                <a href="#"><i class="fa-solid fa-magnifying-glass"></i></a>
                <a href="#"><i class="fa-solid fa-envelope"></i></a>
                <a href="#">?</a>
            </div>
        </div>

        <!-- Thanh cÃ´ng cá»¥ bÃªn trÃ¡i -->
        <div class="left-toolbar">
            <ul>
                <li><img src="../assets/img/logo_3.jpg" alt=""></li>
                <li>1</li>
                <li><i class="fa-solid fa-plus"></i></li>
                <li><i class="fa-regular fa-compass"></i></li>
                <li><i class="fa-solid fa-gear"></i></li>
            </ul>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
            <h2>Khám Phá Câu Lạc Bộ</h2>
            <ul>
                <li><a href="#">Trang Chủ</a></li>
                <li><a href="#">Gaming</a></li>
                <li><a href="#">Âm Nhạc</a></li>
                <li><a href="#">Khoa học & Công Nghệ</a></li>
                <li><a href="#">Thể Thao</a></li>
                <li><a href="#">Giáo Dục</a></li>
            </ul>
        </div>

        <!-- Ná»i dung chÃ­nh -->
        <div class="content">
            <header class="content-header">
                <h1>TÌM NHỮNG CÂU LẠC BỘ DÀNH CHO BẠN TRÊN 4CLUB</h1>
                <p>Luôn có câu lạc bộ phù hợp với bạn trên 4Club</p>
            </header>

            <section class="club-section">
                <h3>Câu Lạc Bộ Nổi Bật</h3>
                <div class="club-list">                              
                </div>
            </section>
        </div>
    </div>
</body>
</html>