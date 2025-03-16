<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4Club - Câu Lạc Bộ</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../assets/css/hoaroi.css">
    <style>
        .petal {
            position: absolute;
            width: 20px;
            height: 20px;
            background: url('../assets/img/effect-img/hoaanhdao.png') no-repeat center center;
            background-size: contain;
            opacity: 0.9;
            animation: fall linear infinite;
        }
    </style>
</head>

<body>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html,
        body {
            font-family: Arial, sans-serif;
            height: 100%;
        }

        .container {
            display: grid;
            width: 100vw;
            height: 100vh;
            grid-template-columns: 1fr 3fr 8fr;
            /* Left-toolbar (1) - Sidebar (3) - Content (8) */
            grid-template-rows: 60px 1fr;
            /* Header chiếm 1 hàng, phần còn lại chiếm toàn bộ */
            grid-template-areas:
                "header header header"
                "left-toolbar sidebar cont";
            overflow: hidden;
        }


        /* Header */
        .header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 60px;
            z-index: 1000;
            grid-area: header;
            display: flex;
            justify-content: right;
            gap: 40%;
            align-items: center;
            color: white;
            padding: 10px 20px;
        }

        .header-title {
            display: flex;
            align-items: center;
            font-size: 18px;
        }

        .header-title i {
            margin-right: 10px;
        }

        .header-icons {
            display: flex;
            gap: 20px;
        }

        .header-icons a {
            color: white;
            font-size: 18px;
            text-decoration: none;
        }

        /* Thanh công cụ tròn bên trái */
        .left-toolbar {
            position: fixed;
            top: 60px;
            left: 0;
            width: 8.3%;
            height: calc(100vh - 60px);
            grid-area: left-toolbar;
            align-items: center;
            padding: 10px;
        }

        .left-toolbar ul {
            list-style: none;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }

        .left-toolbar li {
            width: 40px;
            height: 40px;
            background: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
        }

        .left-toolbar li img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
        }

        /* Sidebar */
        .sidebar {
            position: fixed;
            top: 60px;
            left: 8.4%;
            width: 25%;
            height: calc(100vh - 60px);
            grid-area: sidebar;
            background: #b2d8d8;
            border-top-left-radius: 10px;

        }

        .sidebar .sidebar-header {
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 12px;
            padding: 20px 5px;

        }

        .sidebar-header__item {
            color: #fff;
            background-color: #064273;
            padding: 5px;
            border-radius: 50%;
        }

        .sidebar-header__name {
            font-size: 25px;
            font-weight: 600;
        }

        .sidebar-coverImg {
            width: 100%;
        }

        .sidebar-coverImg img {
            width: 100%;
        }


        .sidebar ul {
            list-style: none;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .sidebar ul .hidden {
            max-height: 0;
            display: none;
            animation: sidebarFadeIn 2s ease-in-out;
        }

        .sidebar ul .show {
            max-height: 300px;
            margin-left: 16px;
        }

        .sidebar ul li a {
            text-decoration: none;
            color: #064273;
            font-size: 16px;
            display: block;
            padding: 12px 0;
            padding-left: 20px;
            transition: all 0.3s ease;
        }

        .sidebar ul li a:hover {
            border-left: 2px solid #064273;
            color: white;
            padding-left: 18px;
        }

        /* Nội dung chính */
        .content {
            grid-area: cont;
            background: #eaf8f2;
            overflow-y: auto;

            height: calc(100vh - 60px);
        }
    </style>
    <div id="petalContainer"></div>
    <jsp:include page="/view/background.jsp" />
    <div class="container">
        <!-- Header -->
        <div class="header">
            <div class="header-title">
                <i class="fa-regular fa-compass"></i>
                <span>Nhóm 1</span>
            </div>
            <div class="header-icons">
                <a href="#"><i class="fa-solid fa-envelope"></i></a>
                <a href="#">?</a>
            </div>
        </div>

        <!-- Thanh cÃ´ng cá»¥ bÃªn trÃ¡i -->
        <div class="left-toolbar">
            <ul>
                <li><img src="../assets/img/logo-img/logo_3.jpg" alt=""></li>
                <li>1</li>
                <li><i class="fa-solid fa-plus"></i></li>
                <li><i class="fa-regular fa-compass"></i></li>
                <li><i class="fa-solid fa-gear"></i></li>
            </ul>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
            <div class="sidebar-header">
                <div class="sidebar-header__item"><i class="fa-solid fa-house"></i></div>
                <div class="sidebar-header__name">Câu Lạc Bộ A</div>
            </div>
            <div class="sidebar-coverImg">
                <img src="../assets/img/login-img/loginnight.jpg" alt="">
            </div>
            <ul>
                <li><a href="#">Diễn đàn</a></li>
                <li><a href="#">Lịch</a></li>
                <li><a href="#">Sự Kiện</a></li>
                <li><a href="#">Đóng tiền</a></li>
                <li>
                    <a href="#" id="toggle-tasks">Nhiệm vụ</a>
                    <ul id="task-list" class="hidden">
                        <li><a href="#">Nhiệm vụ được giao</a></li>
                        <li><a href="#">Giao nhiệm vụ</a></li>
                    </ul>
                </li>
                <li><a href="#">Báo Cáo</a></li>
                <li><a href="#">Nhóm của bạn</a></li>
            </ul>
        </div>

        <!-- Ná»i dung chÃ­nh -->
        <style>
            .content-header {
                color: #064273;
                display: flex;
                padding: 20px 24px;
                justify-content: space-between;
                border-bottom: 1px solid #ccc;
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
        <div class="content">
            <header class="content-header">
                <h2>Thành Viên</h2>
                <div class="content-header_util">
                    <i class="fa-solid fa-scroll"></i>
                    <i class="fa-solid fa-user-group"></i>
                </div>
            </header>
            <style>
                .content-body {
                    display: flex;
                    flex-direction: column;
                }

                .content-filter {
                    width: 100%;
                    padding: 12px 18px;
                    border-bottom: 1px solid #ccc;
                }

                .content-filter td {
                    width: 25%;
                }

                .content-filter td select {
                    width: 80%;
                    padding: 12px 8px;
                    border-radius: 12px;
                    border: 1px solid #ccc
                }

                .content-filter td input {
                    border-radius: 12px;
                    border: 1px solid #ccc;
                }

                .filter-search {
                    width: 100%;
                }

                .filter-time input {
                    padding: 12px 6px;
                }

                .filter-search input {
                    width: 100%;
                    padding: 12px;
                }

                .age-filter input {
                    width: 30%;
                    padding: 12px 8px;
                }

                .status {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                }

                .filter-submit input {
                    padding: 12px 36px;
                    background-color: #064273;
                    color: #fff;
                    border: none;
                    font-size: 16px;
                    font-weight: bold;
                    cursor: pointer;
                    transition: all 0.3s ease-in-out;
                }

                .filter-submit input:hover {
                    background-color: white;
                    border: 2px solid rgb(248, 145, 9);
                    color: rgb(248, 145, 9);
                    transform: scale(1.1);
                    transition: all 0.3s ease-in-out;
                }

                @keyframes funEffect {
                    0% {
                        transform: scale(1);
                        color: orange;
                    }

                    25% {
                        transform: scale(1.2) rotate(-5deg);
                    }

                    50% {
                        transform: scale(1.3) rotate(5deg);
                        color: #ff5733;
                    }

                    75% {
                        transform: scale(1.2) rotate(-5deg);
                        color: #ff9800;
                    }

                    100% {
                        transform: scale(1);
                        color: orange;
                    }
                }

                .filter-submit input:hover {
                    animation: funEffect 3s ease-in-out;
                }
            </style>
            <div class="content-body">
                <div class="content-filter">
                    <form action="#" method="GET">
                        <table>
                            <tr>
                                <td><select name="department" id="">
                                        <option value="1">Văn Hoá</option>
                                        <option value="2">Kinh Tế</option>
                                        <option value="3">Hậu Cần</option>
                                        <option value="4">Truyền Thông</option>
                                        <option value="5">Chuyên Môn</option>
                                    </select>
                                </td>
                                <td class="filter-time">Thời điểm tham gia<input type="date"></td>
                                <td>
                                    <select name="gender" id="">
                                        <option value="1">Nam</option>
                                        <option value="2">Nữ</option>
                                        <option value="3">Giới Tính Khác</option>
                                    </select>
                                </td>
                                <td class="filter-search"><input type="text" placeholder="Name, id, email"></td>
                            </tr>
                            <tr>
                                <td class="age-filter">Age <input type="text" name="ageMin"> - <input type="text"
                                        name="ageMax"> year(s)</td>
                                <td>
                                    <div class="status">
                                        <div class="status-1">Tham gia hoạt động</div>
                                        <div class="status-2">
                                            <div class="status-name"><input type="radio" name="status" value="1"> Chăm
                                            </div>
                                            <div class="status-name"><input type="radio" name="status" value="2"> Lười
                                                chẩy thây</div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <select name="firstName" id="">
                                        <option value="1">Tên (A-Z)</option>
                                        <option value="2">Tên (Z-A)</option>
                                        <option value="1">Họ (A-Z)</option>
                                        <option value="2">Họ (Z-A)</option>
                                    </select>
                                </td>
                                <td class="filter-submit"><input type="submit" value="Filter"></td>
                            </tr>
                        </table>
                    </form>
                </div>

                <style>
                    .content-member-information {
                        display: flex;
                        flex-direction: column;
                    }

                    .content-member-information table {
                        border-collapse: collapse;
                        border: 1px solid #ccc;

                    }

                    .content-member-information tr th,
                    .content-member-information tr td {
                        text-align: center;
                        padding: 12px;
                    }

                    .content-member-information button {
                        padding: 12px 24px;
                        border-radius: 12px;
                        background-color: #064273;
                        border: #064273;
                        color: #fff;
                        cursor: pointer;
                        font-size: 16px;
                        font-weight: bold;
                        transition: background-color 0.3s ease-in-out;
                    }

                    .content-member-information button:hover {
                        background-color: red;
                        animation: shake 0.3s ease-in-out infinite;
                    }

                    /* Hiệu ứng lắc trái phải */
                    @keyframes shake {
                        0% {
                            transform: translateX(0);
                        }

                        25% {
                            transform: translateX(-5px);
                        }

                        50% {
                            transform: translateX(5px);
                        }

                        75% {
                            transform: translateX(-5px);
                        }

                        100% {
                            transform: translateX(0);
                        }
                    }
                </style>
                <div class="content-member-information">
                    <table border="1">
                        <tr>
                            <th>Mã sinh viên</th>
                            <th>Họ</th>
                            <th>Tên</th>
                            <th>Ngày sinh</th>
                            <th>Giới Tính</th>
                            <th>Phòng Ban</th>
                            <th>Chức vụ</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>HE120204</td>
                            <td>Hầu</td>
                            <td>Hoà Inu</td>
                            <td>12/12/2004</td>
                            <td>Bê Đê</td>
                            <td>Kinh tế</td>
                            <td>Sai vặt</td>
                            <td><button value="Delete">Xoá</button></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <script>
            document.getElementById('toggle-tasks').addEventListener('click', function (event) {
                event.preventDefault();
                let taskList = document.getElementById('task-list');
                taskList.classList.toggle('show');
                taskList.classList.toggle('hidden');
            });
        </script>
</body>
<script src="../assets/js/effect-js/roihoa.js"></script>

</html>