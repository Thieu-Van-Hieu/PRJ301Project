<%@page contentType="text/html;charset=UTF-8" language="java"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <div class="content-container">
        <%
            request.setAttribute("contentHeader", "Sự kiện");
        %>
        <jsp:include page="contentHeader.jsp" />

        <div class="content-body">
            <div class="content-filter">
                <form action="#" method="GET">
                    <table>
                        <tr>
                            <td class="filter-time">Ngày đăng <input type="date"></td>
                            <td><select name="type" id="">
                                    <option value="0">Loại sự kiện</option>
                                    <option value="1">ABC</option>
                                    <option value="2">CDF</option>
                                </select>
                            </td>
                            
                            <td class="filter-search"><input type="text" placeholder="Name, id, location"></td>
                        </tr>
                        <tr>
                            <td>
                                <select name="clubName" id="">
                                    <option value="0">Câu Lạc Bộ Tổ Chức</option>
                                    <option value="1">ABC</option>
                                    <option value="2">CDF</option>
                                </select>
                            </td>
                            <td>
                                <select name="status" id="">
                                    <option value="0">Trạng Thái</option>
                                    <option value="1">Đang diễn ra</option>
                                    <option value="2">Sắp ra mắt</option>
                                    <option value="3">Đã kết thúc</option>
                                </select>
                            </td>
                            <td class="filter-submit"><input type="submit" value="Filter"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="createEvent">
                <button class="js-open-modal">Tạo sự kiện</button>
            </div>
            <div class="contentListEvent">
                <div class="event-item">
                    <div class="event-img">IMG</div>
                    <div class="event-info">
                        <h3>Title of event</h3>
                        <p><strong>Câu lạc bộ:</strong> ABC</p>
                        <p><strong>Thời gian:</strong> 12 / 03 / 2025</p>
                        <p><strong>Mô tả:</strong></p>
                    </div>
                    <div class="event-options">...</div>
                </div>

                <div class="event-item">
                    <div class="event-img">IMG</div>
                    <div class="event-info">
                        <h3>Title of event</h3>
                        <p><strong>Câu lạc bộ:</strong> ABC</p>
                        <p><strong>Thời gian:</strong> 12 / 03 / 2025</p>
                        <p><strong>Mô tả:</strong></p>
                    </div>
                    <div class="event-options">...</div>
                </div>
            </div>

            <div class="modal js-modal" id="modal">
                <form id="uploadForm" enctype="multipart/form-data">
                    <div class="modal-container js-modal-container">
                        <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                        <div class="modal-header">Tạo sự kiện</div>
                        <div class="modal-body">
                            <div class="modal-body-header">
                                <div class="modal-body-avatar"><img src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                                </div>
                                <p>Chong cua Bo</p>
                                <input type="hidden" name="userId" value="${userId}" id="">
                                <input type="hidden" name="clubId" value="${clubId}">
                            </div>
                            <table>
                                <tr>
                                    <td><input type="text" name="name" placeholder="Tên Sự Kiện"></td>
                                    <td>
                                        <select name="type">
                                            <option value="1">Hội Thảo</option>
                                            <option value="2">Cuộc Thi</option>
                                            <option value="3">Gặp Gỡ</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="text" id="startDate" name="startDate"
                                            placeholder="Chọn ngày giờ bắt đầu"></td>
                                    <td><input type="text" id="endDate" name="endDate"
                                            placeholder="Chọn ngày giờ kết thúc"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <select id="province" onchange="loadDistricts()">
                                            <option value="">Chọn tỉnh/thành phố</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select id="district" onchange="loadWards()">
                                            <option value="">Chọn quận/huyện</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <select id="ward">
                                            <option value="">Chọn phường/xã</option>
                                        </select>
                                    </td>
                                    <td><input type="text" name="locationInfor" placeholder="Địa chỉ cụ thể"></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <div class="modal-select" id="customFileUpload">
                                            <i class="fa-solid fa-images"></i>
                                            <p>Thêm ảnh gì không người đẹp?</p>
                                        </div>
                                        <input style="display: none;" type="file" id="fileInput" name="file"
                                            required>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <textarea name="description" placeholder="Mô Tả"></textarea>
                                    </td>
                                </tr>
                            </table>
                            <button type="submit" class="btn-submit">Đăng</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <script>
        // Kích hoạt Flatpickr cho ngày giờ bắt đầu & kết thúc
        flatpickr("#startDate", {
            enableTime: true,
            dateFormat: "Y-m-d H:i",
            time_24hr: true,
            position: "below" // Đảm bảo lịch xuất hiện ngay dưới input
        });

        flatpickr("#endDate", {
            enableTime: true,
            dateFormat: "Y-m-d H:i",
            time_24hr: true,
            position: "below"
        });

        // Xử lý chọn file ảnh
        document.getElementById("customFileUpload").addEventListener("click", function () {
            document.getElementById("fileInput").click();
        });
    </script>

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
    </script>

    <script>
        async function loadProvinces() {
            const response = await fetch("https://vn-public-apis.fpo.vn/provinces/getAll?limit=-1");
            const data = await response.json();
            let provinceSelect = document.getElementById("province");

            data.data.data.forEach(province => {
                let option = document.createElement("option");
                option.value = province.code;
                option.textContent = province.name;
                provinceSelect.appendChild(option);
            });
        }

        async function loadDistricts() {
            let provinceId = document.getElementById("province").value;
            let districtSelect = document.getElementById("district");

            districtSelect.innerHTML = '<option value="">Chọn quận/huyện</option>';

            if (provinceId) {
                try {
                    let response = await fetch("https://vn-public-apis.fpo.vn/districts/getAll?limit=-1");
                    let data = await response.json();

                    console.log("API Response:", data); // Kiểm tra API trả về

                    let districts = data.data.data.filter(d => d.parent_code === provinceId);

                    if (districts.length === 0) {
                        console.error("❌ Không có dữ liệu quận/huyện cho tỉnh này.");
                        return;
                    }

                    districts.forEach(district => {
                        let option = document.createElement("option");
                        option.value = district.code;
                        option.textContent = district.name;
                        districtSelect.appendChild(option);
                    });

                } catch (error) {
                    console.error("Lỗi khi tải danh sách quận/huyện:", error);
                }
            }
        }

        async function loadWards() {
            let districtId = document.getElementById("district").value;
            let wardSelect = document.getElementById("ward");

            wardSelect.innerHTML = '<option value="">Chọn phường/xã</option>';

            if (districtId) {
                try {
                    let response = await fetch("https://vn-public-apis.fpo.vn/wards/getAll?limit=-1");
                    let data = await response.json();

                    console.log("API Response (Wards):", data); // Kiểm tra API trả về

                    let wards = data.data.data.filter(w => w.parent_code === districtId);

                    if (wards.length === 0) {
                        console.error("❌ Không có dữ liệu phường/xã cho quận này.");
                        return;
                    }

                    wards.forEach(ward => {
                        let option = document.createElement("option");
                        option.value = ward.code;
                        option.textContent = ward.name;
                        wardSelect.appendChild(option);
                    });

                } catch (error) {
                    console.error("Lỗi khi tải danh sách phường/xã:", error);
                }
            }
        }
        window.onload = loadProvinces;
    </script>
    <script>
        document.getElementById("uploadForm").addEventListener("submit", function (event) {
            event.preventDefault();

            let formData = new FormData();

            formData.append("file", document.getElementById("fileInput").files[0]);

            fetch("${pageContext.request.contextPath}/EventServlet", {
                    method: "POST",
                    body: formData
                })

                .then(response => response.text())
                .then(data => alert(data))
                .catch(error => console.error("Error:", error));
        });
    </script>

</body>


</html>
