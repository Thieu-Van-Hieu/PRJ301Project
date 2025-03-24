<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Iansui&family=Oi&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login-css/setting.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
              integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <jsp:include page="sakura.jsp" />
        <jsp:include page="background.jsp" />
        <jsp:include page="noticeSuccess.jsp"/>
        <div class="account-container">
            <div class="btn-exit"
                 onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?userId=${userInfor.userId}'">
                Esc</div>
            <div class="account-body">
                <div class="account-avatar js-open-modal">
                    <img src="${pageContext.request.contextPath}/assets/img/img-download/${userInformation.avatar}" alt="">
                </div>
                <h3 class="account-name">${userInfor.lastName} ${userInfor.firstName}</h3>
                <div class="information-container">
                    <div class="information-item">
                        <div class="infor">
                            <h4>Tài Khoản: </h4>
                            <h5>${userInfor.userName}</h5>
                        </div>
                        <div class="nothing"></div>
                    </div>
                    <div class="information-item">
                        <div class="infor">
                            <h4>Mật Khẩu: </h4>
                            <h5>****************</h5>
                        </div>
                        <button class="btn-setting" onclick="location.href = '${pageContext.request.contextPath}/view/changepassword.jsp'">Chỉnh sửa</button>
                    </div>
                    <div class="information-item">
                        <div class="infor">
                            <h4>Email: </h4>
                            <h5>${userInfor.email}</h5>
                        </div>
                    </div>
                    <div class="btn-logout"
                         onclick="location.href = '${pageContext.request.contextPath}/UserSettingServlet?action=logout'">Đăng
                        Xuất</div>
                </div>
            </div>
        </div>
        <div class="modal js-modal" id="modal">
            <form action="${pageContext.request.contextPath}/UserSettingServlet" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" value="changeImg">
                <div class="modal-container js-modal-container">
                    <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                    <div class="img-create" id="imgPreview"></div>
                    <div class="modal-select" id="customFileUpload"><i class="fa-solid fa-images"></i>
                        <p>Đổi ảnh gì chưa người đẹp?</p>
                    </div>
                    <input type="file" id="fileInput" name="file" style="display: none;">
                    <input type="submit" class="btn-submit" name="name" value="Đổi">
                </div>
            </form>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/assets/js/login-js/setting.js"></script>
</html>