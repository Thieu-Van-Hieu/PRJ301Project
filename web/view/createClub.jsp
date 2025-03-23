<%-- 
    Document   : createClub
    Created on : Mar 22, 2025, 11:33:11 PM
    Author     : hunggt1572004
--%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tạo Câu Lạc Bộ</title>

</head>

<body>
    <jsp:include page="sakura.jsp" />
    <jsp:include page="background.jsp" />
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
            width: 80vw;
            height: 80vh;
            margin: auto;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            font-family: Arial, sans-serif;
            overflow: auto;
        }

        .form-register {
            display: flex;
            flex-direction: column;
            gap: 15px;
            position: relative;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-row {
            display: flex;
            flex-direction: column;
            gap: 5px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input,
        select,
        textarea {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            width: 100%;
            resize: vertical;
        }

        button {
            background: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: 0.3s;
        }

        button:hover {
            background: #0056b3;
        }

        .img-create {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 150px;
            border: 2px dashed #ccc;
            border-radius: 8px;
            margin-bottom: 10px;
            background: #f8f8f8;
            overflow: hidden;
            position: relative;
        }

        .img-create img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
            position: absolute;
        }

        .modal-select {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 10px;
            cursor: pointer;
            color: #007bff;
            border: 1px solid #007bff;
            border-radius: 5px;
            text-align: center;
            transition: 0.3s;
        }

        .modal-select:hover {
            background: #007bff;
            color: white;
        }

        .btn-exit {
            width: 42px;
            height: 42px;
            border: 1px solid #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 50%;
            position: absolute;
            z-index: 1000;
            top: 24px;
            right: 24px;
            color: #ccc;
            cursor: pointer;
        }
    </style>
    <div class="container">
        <div class="form-register">
            <h2>Tạo Câu Lạc Bộ</h2>
            <div class="btn-exit"
            onclick="location.href = '${pageContext.request.contextPath}/DiscoveryServlet?userId=${userInfor.userId}'">
            Esc</div>
            <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
                <div class="form-row">
                    <label for="clubName">Tên Câu Lạc Bộ:</label>
                    <input type="text" id="clubName" name="clubName" autocomplete="off" required>
                </div>
                <div class="form-row">
                    <label for="type">Type:</label>
                    <select name="type" id="type">
                        <option value="Gaming">Gaming</option>
                        <option value="Âm Nhạc">Âm Nhạc</option>
                        <option value="Khoa Học & Công Nghệ">Khoa Học & Công Nghệ</option>
                        <option value="Thể Thao">Thể Thao</option>
                        <option value="Giáo Dục">Giáo Dục</option>
                    </select>
                </div>

                <div class="form-row">
                    <label for="description">Mô Tả:</label>
                    <textarea id="description" name="description" rows="4" required></textarea>
                </div>
                <div class="form-row">
                    <div class="img-create" id="avatarClubPreview"></div>
                    <div class="modal-select" id="customAvatarFile"><i class="fa-solid fa-images"></i>
                        <p>Thêm ảnh đại diện câu lạc bộ gì không người đẹp?</p>
                    </div>
                    <input type="file" id="avatarClub" name="avatarClub" style="display: none;">

                </div>
                <div class="form-row">
                    <div class="img-create" id="coverImgClubPreview"></div>
                    <div class="modal-select" id="customCoverImgFile"><i class="fa-solid fa-images"></i>
                        <p>Thêm ảnh nền câu lạc bộ gì không người đẹp?</p>
                    </div>
                    <input type="file" id="coverImgClub" name="coverImgClub" style="display: none;">

                </div>
                <button type="submit" style="color: white; font-weight: 700;margin-top: 24px;">Đăng Ký</button>
            </form>
        </div>
    </div>
    <script>
        document.getElementById("customAvatarFile").addEventListener("click", function () {
            document.getElementById("avatarClub").click();
        });
        document.getElementById("customCoverImgFile").addEventListener("click", function () {
            document.getElementById("coverImgClub").click();
        });
        document.getElementById('avatarClub').addEventListener('change', function (event) {
            const file = event.target.files[0]; // Lấy file đầu tiên người dùng chọn
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    const imgElement = document.createElement('img');
                    imgElement.src = e.target.result;
                    imgElement.style.maxWidth = "100%"; // Giới hạn kích thước ảnh
                    imgElement.style.borderRadius = "8px";
                    imgElement.style.marginTop = "10px";

                    const previewDiv = document.getElementById('avatarClubPreview');
                    previewDiv.innerHTML = ""; // Xóa ảnh cũ nếu có
                    previewDiv.appendChild(imgElement); // Thêm ảnh mới vào div
                };
                reader.readAsDataURL(file); // Chuyển file thành URL để hiển thị ảnh
            }
        });
        document.getElementById('coverImgClub').addEventListener('change', function (event) {
            const file = event.target.files[0]; // Lấy file đầu tiên người dùng chọn
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    const imgElement = document.createElement('img');
                    imgElement.src = e.target.result;
                    imgElement.style.maxWidth = "100%"; // Giới hạn kích thước ảnh
                    imgElement.style.borderRadius = "8px";
                    imgElement.style.marginTop = "10px";

                    const previewDiv = document.getElementById('coverImgClubPreview');
                    previewDiv.innerHTML = ""; // Xóa ảnh cũ nếu có
                    previewDiv.appendChild(imgElement); // Thêm ảnh mới vào div
                };
                reader.readAsDataURL(file); // Chuyển file thành URL để hiển thị ảnh
            }
        });
    </script>
</body>
<script src="${pageContext.request.contextPath}/assets/js/valiate/passwordright.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/valiate/signupform.js"></script>

</html>