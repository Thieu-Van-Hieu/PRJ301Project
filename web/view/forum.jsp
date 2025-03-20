<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forum-css/forum.css" />

<div class="content-container">
    <header class="content-header">
        <h2>Diễn đàn</h2>
        <div class="content-header_util">
            <i class="fa-solid fa-scroll"></i>
            <i class="fa-solid fa-user-group" onclick="location.href = '${pageContext.request.contextPath}/MemberServlet'"></i>
            <input type="text" name="" id="" placeholder="Tìm kiếm">
            <i class="fa-solid fa-magnifying-glass content-search"></i>
        </div>
    </header>
    <div class="content-create js-open-modal">
        <div class="content-text">
            <div class="avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
            </div>
            <input type="text" id="commentInput" class="input" placeholder="Content gì chưa người đẹp?">
        </div>
        <div class="content-img">
            <i class="fa-solid fa-images"></i>
            <p>Ảnh / Video</p>
        </div>
    </div>
    <div class="modal js-modal" id="modal">
        <form action="${pageContext.request.contextPath}/ForumServlet" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add">
            <div class="modal-container js-modal-container">
                <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                <div class="modal-header">Tạo Bài Viết</div>
                <input type="hidden" name="createTime" id="createTime" value="">
                <input type="hidden" name="userId" value="1">
                <input type="hidden" name="clubId" value="1">
                <div class="modal-body">
                    <div class="modal-body-header">
                        <div class="modal-body-avatar"><img
                                src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                        </div>
                        <p>Chong cua Bo</p>
                    </div>
                    <input type="text" class="modal-content" name="content" placeholder="   Content gì chưa người đẹp?">
                    <div class="img-create" id="imgPreview"></div>
                    <div class="modal-select" id="customFileUpload"><i class="fa-solid fa-images"></i>
                        <p>Thêm ảnh gì không người đẹp?</p>
                    </div>
                    <input type="file" id="fileInput" name="file">
                    <input type="submit" class="btn-submit" name="name" value="Đăng">
                </div>
            </div>
        </form>
    </div>

    <div class="content-list">
        <c:choose>
            <c:when test="${not empty posts}">
                <c:forEach var="post" items="${posts}">
                    <div class="content-item" data-id="${post.id}">
                        <div class="item-header">
                            <div class="item-avatar">
                                <div class="item-img"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg"
                                                           alt=""></div>
                                <div class="item-name-date">
                                    <div class="item-name">Chong cua Bo</div>
                                    <div class="item-date">${post.createdAt}</div>
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
                            <div class="item-content-text">${post.content}</div>
                            <div class="item-content-img">
                                <img src="${pageContext.request.contextPath}/assets/img/img-download/${post.img}" alt="">
                            </div>
                            <div class="item-content-comment">
                                <div class="item-body-love">
                                    <i class="fa-solid fa-heart"></i>
                                    <p>${post.loves}</p>
                                </div>
                                <div class="item-body-comment">${post.comments} bình luận</div>
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
                            <div class="avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                            </div>
                            <input type="text" id="commentInput" class="input" placeholder="Bình luận gì chưa người đẹp?">
                        </div>
                        <div class="comment-list">
                            <c:choose>
                                <c:when test="${not empty post.postCommentList}">
                                    <c:forEach var="postComment" items="${post.postCommentList}">
                                        <div class="content-text" id="${postCommentList.postId}">
                                            <div class="avatar">
                                                <img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                                            </div>
                                            <div class="comment-body">
                                                <div class="comment-name">Chong cua Bo</div>
                                                <div class="comment-content">${postCommentList.content}</div>
                                            </div>
                                            <div class="item-utils">
                                                <div class="item-extend"><i class="fa-solid fa-quote-right"></i></div>
                                                <div class="item-close"><i class="fa-solid fa-xmark"></i></div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </c:when>


            <c:otherwise>
                <p>Không có bài viết nào để hiển thị.</p>
            </c:otherwise>
        </c:choose>
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
    const imgPreview = document.getElementById('imgPreview');
    const fileInput = document.getElementById('fileInput');

    function showModal() {
        modal.classList.add('open')
    }

    function hideModal() {
        modal.classList.remove('open')
    }

    newContent.addEventListener('click', showModal)

    modalClose.addEventListener('click', hideModal)

    modalClose.addEventListener('click', function () {
        setTimeout(() => {
            document.getElementById('imgPreview').innerHTML = "";
            document.getElementById('fileInput').value = "";
        }, 300);
    });
    modal.addEventListener('click', hideModal)
    modal.addEventListener('click', function () {
        setTimeout(() => {
            document.getElementById('imgPreview').innerHTML = "";
            document.getElementById('fileInput').value = "";
        }, 300);
    });

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
    document.querySelector(".btn-submit").addEventListener("click", function () {
        // Lấy thời gian hiện tại
        let now = new Date();
        let formattedTime = now.toISOString(); // Định dạng: YYYY-MM-DDTHH:mm:ss.sssZ

        // Gán vào input ẩn
        document.getElementById("createTime").value = formattedTime;
    });

    document.getElementById('fileInput').addEventListener('change', function (event) {
        const file = event.target.files[0]; // Lấy file đầu tiên người dùng chọn
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const imgElement = document.createElement('img');
                imgElement.src = e.target.result;
                imgElement.style.maxWidth = "100%"; // Giới hạn kích thước ảnh
                imgElement.style.borderRadius = "8px";
                imgElement.style.marginTop = "10px";

                const previewDiv = document.getElementById('imgPreview');
                previewDiv.innerHTML = ""; // Xóa ảnh cũ nếu có
                previewDiv.appendChild(imgElement); // Thêm ảnh mới vào div
            };
            reader.readAsDataURL(file); // Chuyển file thành URL để hiển thị ảnh
        }
    });
</script>