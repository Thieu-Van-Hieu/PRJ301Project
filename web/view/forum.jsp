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
</head>

<body>
    <jsp:include page="sakura.jsp" />
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
    <style>
        .content-list {
            display: flex;
            flex-direction: column;
            gap: 24px;
            margin: 12px 32px;
        }

        .content-item {
            display: flex;
            flex-direction: column;
            background-color: #fff;
            border-radius: 12px;
        }

        .item-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 16px 24px;
        }

        .item-avatar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 12px;
        }

        .item-img {
            width: 46px;
            height: 46px;
            display: flex;
            align-items: center;
            justify-content: center;

        }

        .content-item .item-img img {
            border-radius: 50%;
        }

        .content-item img {
            width: 100%;
        }

        .item-name-date {
            display: flex;
            flex-direction: column;
            justify-content: left;
            align-items: center;

        }

        .item-name {
            font-size: 18px;
            font-weight: 600;
        }

        .item-date {
            font-size: 12px;
        }

        .item-utils {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 12px;
            font-size: 24px;
            opacity: 0.7;
            position: relative;
        }

        .item-utils ul {
            display: none;
            background-color: #fff;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            position: absolute;
            width: 120px;
            top: 32px;
            right: 28px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.6);
            border-radius: 12px;
            padding: 4px;
        }

        .item-utils li {
            list-style-type: none;
            font-size: 16px;
            padding: 6px 4px 6px 20px;
            border-radius: 12px;
            width: 95%;
            cursor: pointer;
        }

        .item-utils li:hover {
            background-color: #575656;
            color: #fff;
        }

        .item-extend {
            padding: 8px 12px;
        }

        .item-close {}

        .item-body {}

        .item-content-text {
            padding: 0 12px 6px;
        }

        .item-content-comment {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 0 12px;
            border-bottom: 1px solid #ccc;
            padding: 12px 0;
        }

        .item-body-love {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 5px;
        }

        .item-body-love i {
            font-size: 18px;
            color: pink;
        }

        .item-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 48px;
        }

        .item-footer-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 5px;
            font-size: 24px;
            padding: 12px 48px 8px;
            cursor: pointer;
        }

        .input-comment {
            padding-left: 12px;
            display: none;
        }

        .comment-list {
            padding: 0px 24px 12px;
            display: none;
        }

        .comment-body {
            display: flex;
            flex-direction: column;
            gap: 4px;
            width: 85%;
        }

        .comment-name {
            font-size: 24px;
            font-weight: 600;
        }

        .comment-content {
            font-size: 18px;
        }

        @keyframes likeAnimation {
            0% {
                transform: scale(1) translateY(0);
            }

            20% {
                transform: scale(1.5) translateY(-10px);
            }

            40% {
                transform: scale(1.3) translateY(-5px);
            }

            60% {
                transform: scale(1.4) translateY(-8px) rotate(10deg);
            }

            80% {
                transform: scale(1.3) translateY(-5px) rotate(-10deg);
            }

            100% {
                transform: scale(1) translateY(0);
            }
        }

        @keyframes unlikeAnimation {
            0% {
                transform: scale(1) translateY(0);
            }

            20% {
                transform: scale(0.8) translateY(5px);
            }

            40% {
                transform: scale(0.9) translateY(3px);
            }

            60% {
                transform: scale(0.85) translateY(4px) rotate(-5deg);
            }

            80% {
                transform: scale(0.9) translateY(3px) rotate(5deg);
            }

            100% {
                transform: scale(1) translateY(0);
            }
        }

        .heart-animate {
            animation: likeAnimation 3s ease;
        }

        .heart-unlike {
            animation: unlikeAnimation 0.8s ease;
        }
    </style>
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
    <style>
        .content-create {
            display: flex;
            flex-direction: column;
            background-color: #fff;
            padding: 5px 24px;
            border-radius: 12px;
            margin: 12px 32px;
        }

        .content-text {
            display: flex;
            gap: 12px;
            padding: 12px 0;
            border-bottom: 1px solid #ccc;
            justify-content: space-between;
            align-items: center;
        }

        .content-text .avatar {
            width: 32px;
            height: 32px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .content-text .input {
            padding: 8px 6px;
            width: 95%;
            border-radius: 12px;
            border: none;
        }

        .content-text img {
            width: 100%;
            border-radius: 50%;
        }

        .content-img {
            display: flex;
            gap: 12px;
            justify-content: center;
            align-items: center;
            color: #064273;
            margin-top: 12px;
        }

        #fileInput {
            display: none;
        }

        .modal {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background-color: rgba(0, 0, 0, 0.4);
            align-items: center;
            justify-content: center;
            display: none;
        }

        .modal.open {
            display: flex;
        }

        .modal-container {
            background-color: #fff;
            width: 900px;
            max-width: calc(100% - 32px);
            min-height: 200px;
            position: relative;
            animation: modalFadeIn ease 0.5s;
            border-radius: 12px;
        }

        .modal-close {
            position: absolute;
            right: 24px;
            top: 16px;
            color: #000;
            cursor: pointer;
            opacity: 0.8;
            font-size: 24px;
            background-color: #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 36px;
            height: 36px;
            border-radius: 50%;
        }

        .modal-header {
            text-align: center;
            padding: 24px 0;
            font-size: 32px;
            font-weight: 600;
            border-bottom: 1px solid #ccc;
        }

        .modal-body {
            display: flex;
            flex-direction: column;
            padding: 12px 18px;
        }

        .modal-body .modal-body-header {
            display: flex;
            justify-content: left;
            align-items: center;
            gap: 12px;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 18px;
        }

        .modal-body .modal-body-avatar {
            width: 36px;
            height: 36px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .modal-body-avatar img {
            width: 100%;
            border-radius: 50%;
        }

        .modal-content {
            padding-bottom: 68px;
            border: none;
            font-size: 24px;
        }

        .modal-content::placeholder {
            position: absolute;
            top: 0;
            left: 0;
            transform: translateY(4px);
            font-size: 24px;
            color: gray;
        }

        .modal-select {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 12px 0;
            font-size: 18px;
            gap: 8px;
            cursor: pointer;
        }

        .modal .btn-submit {
            width: 100%;
            border: none;
            padding: 12px 0;
            font-size: 18px;
            font-weight: 600;
            color: #fff;
            background-color: #064273;
            border-radius: 16px;
            margin-top: 24px;
            cursor: pointer;
        }

        @keyframes sidebarFadeIn {
            from {
                opacity: 0;
                transform: translateY(-50px);
            }

            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes modalFadeIn {
            from {
                opacity: 0;
                transform: translateY(-150px);
            }

            to {
                opacity: 1;
                transform: translateY(0);
            }
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
                <li><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></li>
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
                <img src="${pageContext.request.contextPath}/ssets/img/login-img/loginnight.jpg" alt="">
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
       
        <div class="content">
            <header class="content-header">
                <h2>Diễn đàn</h2>
                <div class="content-header_util">
                    <i class="fa-solid fa-scroll"></i>
                    <i class="fa-solid fa-user-group"></i>
                    <input type="text" name="" id="" placeholder="Tìm kiếm">
                    <i class="fa-solid fa-magnifying-glass content-search"></i>
                </div>
            </header>

            
            <div class="content-create js-open-modal">
                <div class="content-text">
                    <div class="avatar"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                    <input type="text" id="commentInput" class="input" placeholder="Content gì chưa người đẹp?">
                </div>
                <div class="content-img">
                    <i class="fa-solid fa-images"></i>
                    <p>Ảnh / Video</p>
                </div>
            </div>
            <div class="modal js-modal" id="modal">
                <form id="uploadForm" enctype="multipart/form-data">
                    <div class="modal-container js-modal-container">
                        <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                        <div class="modal-header">Tạo Bài Viết</div>
                        <div class="modal-body">
                            <div class="modal-body-header">
                                <div class="modal-body-avatar"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt="">
                                </div>
                                <p>Chong cua Bo</p>
                            </div>
                            <input type="text" class="modal-content" placeholder="Content gì chưa người đẹp?">
                            <div class="modal-select" id="customFileUpload"><i class="fa-solid fa-images"></i>
                                <p>Thêm ảnh gì không người đẹp?</p>
                            </div>
                            <input type="file" id="fileInput" name="file" required>
                            <button type="submit" class="btn-submit">Đăng</button>
                        </div>

                    </div>
                </form>
            </div>
            
            <div class="content-list">
                <div class="content-item" data-id="1">
                    <div class="item-header">
                        <div class="item-avatar">
                            <div class="item-img"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                            <div class="item-name-date">
                                <div class="item-name">Chong cua Bo</div>
                                <div class="item-date">14 Tháng 3, 6:36 PM</div>
                            </div>
                        </div>
                        <div class="item-utils">
                            <div class="item-extend"><i class="fa-solid fa-quote-right"></i></div>
                            <ul>
                                <li>Chỉnh sửa</li>
                                <li>Ẩn</li>
                            </ul>
                            <div class="item-close"><i class="fa-solid fa-xmark"></i></div>
                        </div>
                    </div>
                    <div class="item-body">
                        <div class="item-content-text">Mùa xuân sang có hoa anh đào</div>
                        <div class="item-content-img"><img src="${pageContext.request.contextPath}/ssets/img/login-img/loginmorning.jpg" alt=""></div>
                        <div class="item-content-comment">
                            <div class="item-body-love">
                                <i class="fa-solid fa-heart"></i>
                                <p>123</p>
                            </div>
                            <div class="item-body-comment">1 bình luận</div>
                        </div>
                    </div>
                    <div class="item-footer">
                        <div class="item-footer-item love">
                            <i class="fa-regular fa-heart"></i>
                            <p>Thích</p>
                        </div>
                        <div class="item-footer-item comment">
                            <i class="fa-regular fa-comment"></i>
                            <p>Bình Luận</p>
                        </div>
                    </div>
                    <div class="content-text input-comment">
                        <div class="avatar"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                        <input type="text" id="commentInput" class="input" placeholder="Bình luận gì chưa người đẹp?">
                    </div>
                    <div class="comment-list">
                        <div class="content-text">
                            <div class="avatar"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                            <div class="comment-body">
                                <div class="comment-name">Chong cua Bo</div>
                                <div class="comment-content">Bài viết thật hay và sáng tạo</div>
                            </div>
                            <div class="item-utils">
                                <div class="item-extend"><i class="fa-solid fa-quote-right"></i></div>
                                <div class="item-close"><i class="fa-solid fa-xmark"></i></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="content-item" data-id="2">
                    <div class="item-header">
                        <div class="item-avatar">
                            <div class="item-img"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                            <div class="item-name-date">
                                <div class="item-name">Chong cua Bo</div>
                                <div class="item-date">14 Tháng 3, 6:20 PM</div>
                            </div>
                        </div>
                        <div class="item-utils">
                            <div class="item-extend"><i class="fa-solid fa-quote-right"></i></div>
                            <ul>
                                <li>Chỉnh sửa</li>
                                <li>Ẩn</li>
                            </ul>
                            <div class="item-close"><i class="fa-solid fa-xmark"></i></div>
                        </div>
                    </div>
                    <div class="item-body">
                        <div class="item-content-text">Đêm rồi em có nhớ anh không?</div>
                        <div class="item-content-img"><img src="${pageContext.request.contextPath}/ssets/img/login-img/loginnight.jpg" alt=""></div>
                        <div class="item-content-comment">
                            <div class="item-body-love">
                                <i class="fa-solid fa-heart"></i>
                                <p>123</p>
                            </div>
                            <div class="item-body-comment">1 bình luận</div>
                        </div>
                    </div>
                    <div class="item-footer">
                        <div class="item-footer-item love">
                            <i class="fa-regular fa-heart"></i>
                            <p>Thích</p>
                        </div>
                        <div class="item-footer-item comment">
                            <i class="fa-regular fa-comment"></i>
                            <p>Bình Luận</p>
                        </div>
                    </div>
                    <div class="content-text input-comment">
                        <div class="avatar"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                        <input type="text" id="commentInput" class="input" placeholder="Bình luận gì chưa người đẹp?">
                    </div>
                    <div class="comment-list">
                        <div class="content-text">
                            <div class="avatar"><img src="${pageContext.request.contextPath}/ssets/img/logo-img/logo_3.jpg" alt=""></div>
                            <div class="comment-body">
                                <div class="comment-name">Chong cua Bo</div>
                                <div class="comment-content">Bài viết thật hay và sáng tạo</div>
                            </div>
                            <div class="item-utils">
                                <div class="item-extend"><i class="fa-solid fa-quote-right"></i></div>
                                <div class="item-close"><i class="fa-solid fa-xmark"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
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

        function openModal() {
            document.getElementById('modal').style.display = 'flex';
        }

        function closeModal() {
            document.getElementById('modal').style.display = 'none';
        }

        document.getElementById("customFileUpload").addEventListener("click", function () {
            document.getElementById("fileInput").click();
        })

        const newContent = document.querySelector('.js-open-modal')
        const modal = document.querySelector('.js-modal')
        const modalContainer = document.querySelector('.js-modal-container')
        const modalClose = document.querySelector('.js-modal-close')

        function showModal() {
            modal.classList.add('open')
        }

        function hideModal() {
            modal.classList.remove('open')
        }

        newContent.addEventListener('click', showModal)

        modalClose.addEventListener('click', hideModal)

        modal.addEventListener('click', hideModal)

        modalContainer.addEventListener('click', function (event) {
            event.stopPropagation();
        })

        document.querySelectorAll('.content-item').forEach((item) => {
            const statusId = item.getAttribute('data-id');

            const loveBtn = item.querySelector('.love');
            const commentBtn = item.querySelector('.comment');
            const commentBox = item.querySelector('.input-comment');
            const commentList = item.querySelector('.comment-list');
            const heartIcon = loveBtn.querySelector('i');
            const extendBtn = item.querySelector('.item-extend');
            const utils = item.querySelector('.item-utils')
            const menu = utils.querySelector('ul');
            const hideOption = menu.querySelector('li:nth-child(2)');

            // if (localStorage.getItem(`hidden-${statusId}`)) {
            //     item.style.display = 'none';
            // }

            loveBtn.addEventListener('click', function () {
                if (heartIcon.classList.contains('fa-regular')) {
                    heartIcon.classList.remove('fa-regular');
                    heartIcon.classList.add('fa-solid', 'heart-animate');
                    heartIcon.style.color = 'pink';

                    setTimeout(() => heartIcon.classList.remove('heart-animate'), 3000);
                } else {
                    heartIcon.classList.remove('fa-solid');
                    heartIcon.classList.add('fa-regular', 'heart-unlike');
                    heartIcon.style.color = '';

                    setTimeout(() => heartIcon.classList.remove('heart-unlike'), 800);
                }
            });

            commentBtn.addEventListener('click', function () {
                if (commentBox.style.display === "none" || commentBox.style.display === "") {
                    commentBox.style.display = "flex";
                    commentList.style.display = "block";
                } else {
                    commentBox.style.display = "none";
                    commentList.style.display = "none";
                }
            });

            extendBtn.addEventListener('click', function (event) {
                event.stopPropagation(); // Ngăn chặn sự kiện lan ra ngoài
                menu.style.display = (menu.style.display === 'flex') ? 'none' : 'flex';
            });

            hideOption.addEventListener('click', function () {
                item.style.display = 'none';
            });
            // Ẩn vĩnh viễn 
            // hideOption.addEventListener('click', function () {
            //     item.style.display = 'none';
            //     localStorage.setItem(`hidden-${statusId}`, true); // Lưu trạng thái ẩn
            // });

            // ✅ Click ra ngoài để ẩn menu
            document.addEventListener('click', function (event) {
                if (!extendBtn.contains(event.target) && !menu.contains(event.target)) {
                    menu.style.display = 'none';
                }
            });
        });
    </script>

</body>

</html>