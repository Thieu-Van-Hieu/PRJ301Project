<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>4Club - Kham Pha</title>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/discovery-css/discovery.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
              integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <jsp:include page="/view/background.jsp" />
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

            <div class="left-toolbar">
                <ul>
                    <li><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt=""></li>
                        <c:forEach var="clubItem" items="${clubListItems}">
                        <li id="${clubItem.id}" onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?action=open&clubId=${clubItem.id}'"><img
                                src="${pageContext.request.contextPath}/assets/img/img-download/${clubItem.name}"
                                alt="alt" /></li>
                        </c:forEach>
                    <li onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?action=createClub'"><i class="fa-solid fa-plus"></i></li>
                    <li onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet'"><i class="fa-regular fa-compass"></i></li>
                    <li onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?action=setting'"><i class="fa-solid fa-gear"></i></li>
                </ul>
            </div>

            <!-- Sidebar -->
            <div class="sidebar">
                <h2>Khám Phá Câu Lạc Bộ</h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/DiscoveryServlet">Trang Chủ</a></li>
                    <li><a href="${pageContext.request.contextPath}/DiscoveryServlet?action=filter&typeId=1">Gaming</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/DiscoveryServlet?action=filter&typeId=2">Âm Nhạc</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/DiscoveryServlet?action=filter&typeId=3">Khoa học &
                            Công Nghệ</a></li>
                    <li><a href="${pageContext.request.contextPath}/DiscoveryServlet?action=filter&typeId=4">Thể Thao</a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/DiscoveryServlet?action=filter&typeId=5">Giáo
                            Dục</a></li>
                </ul>
            </div>

            <!-- Ná»i dung chÃ­nh -->
            <div class="content">
                <header class="content-header">
                    <h1 id="pot">Tìm Những Câu Lạc Bộ Dành Cho Bạn Trên 4Club</h1>
                    <p>Luôn có câu lạc bộ phù hợp với bạn trên 4Club</p>
                </header>

                <section class="club-section">
                    <h2>Câu Lạc Bộ Nổi Bật</h2>
                    <div class="club-list">
                        <c:forEach var="club" items="${clubList}">
                            <div class="club-item" id="${club.id}">
                                <div class="club-item-header">
                                    <img src="${pageContext.request.contextPath}/assets/img/img-download/${club.coverImage}"
                                         alt="coverImage">
                                </div>
                                <div class="club-item-body">
                                    <div class="club-avatar">
                                        <img src="${pageContext.request.contextPath}/assets/img/img-download/${club.avatarClub}"
                                             alt="">
                                    </div>
                                    <div class="club-infor">
                                        <h3>${club.name}</h3>
                                        <h4>Chủ Nhiệm: ${club.clubPresidentName}</h4>
                                        <h4>Loại: ${club.type}</h4>
                                        <p style="display: none;">${club.description}</p>
                                        <input type="hidden" name="name" id="clubIdItem" value="${club.id}">
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>

                <div class="modal js-modal" id="modal">
                    <form action="${pageContext.request.contextPath}/RequestClubServlet" method="POST">
                        <input type="hidden" name="action" value="createRequest">
                        <div class="modal-container js-modal-container">
                            <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                            <div class="modal-header">
                                <img src="" alt="coverImage">
                            </div>
                            <input type="hidden" name="createTime" id="createTime" value="">
                            <input type="hidden" name="clubId" id="clubId">
                            <input type="hidden" name="userId" value="${userId}">
                            <input type="hidden" name="studentId" value="${userInformation.studentId}">
                            <input type="hidden" name="userName" value="${userInformation.firstName}">
                            <div class="modal-body">
                                <div class="modal-body-header">
                                    <div class="modal-club-avatar">
                                        <img src="" alt="">
                                    </div>
                                    <div class="modal-club-infor">
                                        <h3 id="clubName"></h3>
                                        <h4 id="clubPresidentName"></h4>
                                        <h4 id="type"></h4>
                                        <p id="description"></p>
                                    </div>
                                </div>
                                <input type="submit" class="btn-submit" name="name" value="Xin Gia Nhập">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        function addTimestamp() {
            const timestampInput = document.getElementById('createTime');
            if (timestampInput) {
                timestampInput.value = new Date().toISOString();
            }
        }
        document.addEventListener("DOMContentLoaded", function () {
            const content = document.querySelector(".content");
            const sidebar = document.querySelector(".sidebar");

            function checkScroll(element) {
                if (element.scrollTop > 0) {
                    element.style.overflowY = "auto";
                } else {
                    setTimeout(() => {
                        element.style.overflowY = "hidden";
                    }, 300);
                }
            }

            function enableScroll(element) {
                element.style.overflowY = "auto";
            }

            [content, sidebar].forEach(element => {
                if (element) {
                    element.addEventListener("wheel", () => enableScroll(element));
                    element.addEventListener("touchmove", () => enableScroll(element));
                    element.addEventListener("scroll", () => checkScroll(element));
                }
            });
        });

        document.addEventListener("DOMContentLoaded", function () {
            const modal = document.querySelector('.js-modal');
            const modalClose = document.querySelector('.js-modal-close');
            const modalContainer = document.querySelector('.js-modal-container');


            const clubItems = document.querySelectorAll('.club-item');

            clubItems.forEach(item => {
                item.addEventListener('click', function () {
                    const clubName = this.querySelector(".club-infor h3").innerText;
                    const clubPresidentName = this.querySelector(".club-infor h4:nth-of-type(1)").innerText;
                    const clubType = this.querySelector(".club-infor h4:nth-of-type(2)").innerText;
                    const coverImageSrc = this.querySelector(".club-item-header img").src;
                    const avatarImageSrc = this.querySelector(".club-avatar img").src;
                    const clubDiscription = this.querySelector(".club-infor p").innerText;
                    const clubId = this.querySelector("#clubIdItem").value;

                    document.getElementById("clubName").innerText = clubName;
                    document.getElementById("clubPresidentName").innerText = "Chủ Nhiệm: " + clubPresidentName;
                    document.getElementById("type").innerText = "Loại: " + clubType;
                    document.querySelector(".modal-header img").src = coverImageSrc;
                    document.querySelector(".modal-club-avatar img").src = avatarImageSrc;
                    document.getElementById("description").innerText = clubDiscription;
                    document.getElementById("clubId").value = clubId;
                    modal.classList.add('open');
                });
            });

            // Đóng modal khi click vào nút đóng
            modalClose.addEventListener('click', function () {
                modal.classList.remove('open');
            });

            // Đóng modal khi click ra ngoài modal-container
            modal.addEventListener('click', function (event) {
                if (!modalContainer.contains(event.target)) {
                    modal.classList.remove('open');
                }
            });

            const btnSubmit = document.querySelector('.btn-submit');
            if (btnSubmit) {
                btnSubmit.addEventListener('click', addTimestamp);
            }
        });


    </script>

</html>