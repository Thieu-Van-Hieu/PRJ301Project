<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="content-container">
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
        <form id="uploadForm" enctype="multipart/form-data">
            <div class="modal-container js-modal-container">
                <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                <div class="modal-header">Tạo Bài Viết</div>
                <div class="modal-body">
                    <div class="modal-body-header">
                        <div class="modal-body-avatar"><img
                                src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                        </div>
                        <p>Chong cua Bo</p>
                    </div>
                    <input type="text" class="modal-content" placeholder="   Content gì chưa người đẹp?">
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
                    <div class="item-img"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg"
                            alt=""></div>
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
                <div class="item-content-img"><img
                        src="${pageContext.request.contextPath}/assets/img/login-img/loginmorning.jpg" alt=""></div>
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
                <div class="avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                </div>
                <input type="text" id="commentInput" class="input" placeholder="Bình luận gì chưa người đẹp?">
            </div>
            <div class="comment-list">
                <div class="content-text">
                    <div class="avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg"
                            alt=""></div>
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
                    <div class="item-img"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg"
                            alt=""></div>
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
                <div class="item-content-img"><img
                        src="${pageContext.request.contextPath}/assets/img/login-img/loginnight.jpg" alt=""></div>
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
                <div class="avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                </div>
                <input type="text" id="commentInput" class="input" placeholder="Bình luận gì chưa người đẹp?">
            </div>
            <div class="comment-list">
                <div class="content-text">
                    <div class="avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg"
                            alt=""></div>
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