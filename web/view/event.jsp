<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="entity.*" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/event-css/event.css">
<script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dto/Event.js"></script>
<script>
    const socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/event");

    // Khi kết nối thành công
    socket.onopen = function () {
        console.log("Kết nối WebSocket thành công!");
        let action = "${action}";
        console.log("Action: ", action);
        if (action === "createEvent") {
            console.log("Gửi tin nhắn");
            let eventElement = document.querySelector(".contentListEvent .event-item:last-child");
            sendMessage(eventElement, action);
        } else if (action === "deleteEvent") {
            let eventElement = new Event("${eventId}", "", "${member.clubId}", "", "${startDate}", "${endDate}", "");
            let message = {
                action: "deleteEvent",
                data: eventElement,
            };
            socket.send(JSON.stringify(message));
        }
    };
    function isUserAtBottom() {
        let messageContainer = $(".contentListEvent");
        console.log(messageContainer.scrollHeight - messageContainer.scrollTop - messageContainer.clientHeight);
        return Math.abs(messageContainer.scrollHeight - messageContainer.scrollTop - messageContainer.clientHeight) < 5;
    }

    function scrollToBottom() {
        let messageContainer = $(".contentListEvent");
        messageContainer.scrollTop = messageContainer.scrollHeight;
    }

    function getNode(eventObj) {
        const eventDiv = document.createElement("div");
        eventDiv.id = eventObj.id;
        eventDiv.className = "event-item";

        const imgDiv = document.createElement("div");
        imgDiv.className = "event-img";
        const img = document.createElement("img");
        img.src = eventObj.imgUrl;
        img.alt = "Event Image";
        imgDiv.appendChild(img);

        const infoDiv = document.createElement("div");
        infoDiv.className = "event-info";

        const title = document.createElement("h3");
        title.textContent = eventObj.name;
        infoDiv.appendChild(title);

        const clubInfo = document.createElement("p");
        clubInfo.innerHTML = `<strong>Câu lạc bộ:</strong> ` + eventObj.clubName;
        infoDiv.appendChild(clubInfo);

        const startDateInfo = document.createElement("p");
        startDateInfo.textContent = `Start Date: ` + eventObj.startDate;
        infoDiv.appendChild(startDateInfo);

        const endDateInfo = document.createElement("p");
        endDateInfo.textContent = `End Date: ` + eventObj.endDate;
        infoDiv.appendChild(endDateInfo);

        const optionsDiv = document.createElement("div");
        optionsDiv.className = "event-options";
        // Add additional options here if needed

        eventDiv.appendChild(imgDiv);
        eventDiv.appendChild(infoDiv);
        eventDiv.appendChild(optionsDiv);

        return eventDiv;
    }

    function createEvent(eventObj) {
        let eventContainer = $(".contentListEvent");
        if (eventContainer.querySelector(".event-item[id='" + eventObj.id + "']")) {
            return;
        }

        let isBottom = isUserAtBottom();

        eventContainer.appendChild(getNode(eventObj));
        if (isBottom) {
            scrollToBottom();
        }
    }

    function deleteEvent(eventObj) {
        let eventContainer = $(".contentListEvent");
        if (!eventContainer.querySelector(".event-item[id='" + eventObj.id + "']")) {
            return;
        }

        eventContainer.querySelector(".event-item[id='" + eventObj.id + "']").remove();
    }

    // When receiving a message from the server
    socket.onmessage = function (event) {
        let message = JSON.parse(event.data);
        console.log("Received message from Server: ", message.data);

        let actionHandlers = {
            "createEvent": createEvent,
            "deleteEvent": deleteEvent
        };

        if (actionHandlers[message.action]) {
            actionHandlers[message.action](message.data);
        }
    };

    // When an error occurs
    socket.onerror = function (error) {
        console.error("WebSocket error: ", error);
    };

    // Gửi tin nhắn lên Server
    function sendMessage(element, action) {
        event.stopPropagation(); // Ngăn chặn sự kiện click lan ra ngoài

        if ("${member.role}" !== "Chủ nhiệm") {
            alert("Bạn không có quyền thực hiện hành động này!");
            return;
        }

        let eventElement = element.closest(".event-item");
        console.log("Element:", element);
        let id = eventElement?.getAttribute("id");
        let name = eventElement?.querySelector(".event-info h3")?.textContent;
        let clubId = "${member.clubId}";
        let clubName = eventElement?.querySelector(".event-info p strong")?.nextSibling?.nodeValue.trim();
        let startDate = eventElement?.querySelector(".event-info p:nth-child(3)")?.textContent.replace("Start Date: ", "");
        let endDate = eventElement?.querySelector(".event-info p:nth-child(4)")?.textContent.replace("End Date: ", "");
        let imgUrl = eventElement?.querySelector(".event-img img")?.getAttribute("src");

        let eventData = new Event(id, name,clubId, clubName, startDate, endDate, imgUrl);

        let message = {
            action: action,
            data: eventData,
        };
        socket.send(JSON.stringify(message));
    }
</script>
<div class="content-container" style="overflow-y: overlay;">
    <%
    request.setAttribute("contentHeader", "Sự kiện");
    %>
    <jsp:include page="contentHeader.jsp" />

    <div class="content-body">
        <div class="content-filter">
            <form action="${pageContext.request.getContextPath()}/EventServlet" method="GET">
                <input type="hidden" value="filter" name="action">
                <table>
                    <tr>
                        <td class="filter-time">Ngày Đăng <input type="date" name="date"></td>
                        <td>
                            <select name="type" id="id">
                                <c:forEach var="eventType" items="${eventTypes}">
                                    <option value="${eventType.getTypeId()}">${eventType.getTypeName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="filter-search"><input type="text" placeholder="Name, id, location" name="search">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select name="clubId">
                                <c:forEach var="clubDiscription" items="${clubDescriptions}">
                                    <option value="${clubDiscription.getId()}">${clubDiscription.getName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="status">
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
            <c:forEach var="eventDescription" items="${eventDescriptions}">
                <div class="event-item" id="${eventDescription.getEventId()}">
                    <div class="event-img"><img
                            src="${pageContext.request.contextPath}/assets/img/img-download/${eventDescription.getImg()}"
                            alt="Event Image"></div>
                    <div class="event-info">
                        <h3>${eventDescription.getEventName()}</h3>
                        <p><strong>Câu lạc bộ:</strong> ${eventDescription.getClubName()}</p>
                        <p>Start Date: ${eventDescription.getStartDate()}</p>
                        <p>End Date: ${eventDescription.getEndDate()}</p>
                    </div>
                    <c:if test="${member.role eq 'Chủ nhiệm'}">
                    <div class="event-options">
                        <form action="${pageContext.request.contextPath}/EventServlet" method="post" id="${eventDescription.getEventId()}">
                            <input type="hidden" name="eventId" value="${eventDescription.getEventId()}">
                            <input type="hidden" name="clubId" value="${member.clubId}">
                            <input type="hidden" name="startDate" value="${eventDescription.getStartDate()}">
                            <input type="hidden" name="endDate" value="${eventDescription.getEndDate()}">
                            <input type="hidden" name="action" value="deleteEvent">
                            <button type="submit" class="event__btn--delete">Xóa</button>
                        </form>
                        <div class="event__btn--more">...</div>
                    </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>

        <div class="modal js-modal" id="modal">
            <form action="${pageContext.request.contextPath}/EventServlet" method="POST" enctype="multipart/form-data">
                <div class="modal-container js-modal-container">
                    <div class="modal-close js-modal-close"><i class="fa-solid fa-xmark"></i></div>
                    <div class="modal-header">Tạo sự kiện</div>
                    <div class="modal-body">
                        <div class="modal-body-header">
                            <div class="modal-body-avatar"><img
                                    src="${pageContext.request.contextPath}/assets/img/logo-img/logo_3.jpg" alt="">
                            </div>
                            <p>Chong cua Bo</p>
                            <input type="hidden" name="userId" value="${member.id}">
                            <input type="hidden" name="clubId" value="${member.clubId}">
                            <input type="hidden" name="province" value="" id="provinceText">
                            <input type="hidden" name="district" value="" id="districtText">
                            <input type="hidden" name="ward" value="" id="wardText">
                            <input type="hidden" name="action" value="add">
                        </div>
                        <table>
                            <tr>
                                <td><input type="text" name="nameEvent" placeholder="Tên Sự Kiện"></td>
                                <td>
                                    <select name="type" id="id">
                                        <c:forEach var="eventType" items="${eventTypes}">
                                            <option value="${eventType.getTypeId()}">${eventType.getTypeName()}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="text" id="startDate" name="startDate"
                                        placeholder="Chọn ngày giờ bắt đầu"></td>
                                <td><input type="text" id="endDate" name="endDate" placeholder="Chọn ngày giờ kết thúc">
                                </td>
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
                                    <div class="img-create" id="imgPreview"></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div class="modal-select" id="customFileUpload">
                                        <i class="fa-solid fa-images"></i>
                                        <p>Thêm ảnh gì không người đẹp?</p>
                                    </div>
                                    <input style="display: none;" type="file" id="fileInput" name="file">
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

    modalClose.addEventListener('click', function () {
        setTimeout(() => {
            document.getElementById('imgPreview').innerHTML = "";
            document.getElementById('fileInput').value = "";
        }, 300);
    });
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
    // Hàm cập nhật giá trị vào input hidden
    function updateHiddenInput(selectElement, hiddenInputId) {
        const selectedOption = selectElement.options[selectElement.selectedIndex];
        document.getElementById(hiddenInputId).value = selectedOption.textContent;
    }

    // Khi chọn tỉnh/thành phố
    document.getElementById("province").addEventListener("change", function () {
        updateHiddenInput(this, "provinceText");

        // Reset quận/huyện và phường/xã về mặc định
        document.getElementById("district").innerHTML = '<option value="">Chọn quận/huyện</option>';
        document.getElementById("ward").innerHTML = '<option value="">Chọn phường/xã</option>';

        // Reset input hidden của quận/huyện và phường/xã
        document.getElementById("districtText").value = "";
        document.getElementById("wardText").value = "";
    });

    // Khi chọn quận/huyện
    document.getElementById("district").addEventListener("change", function () {
        updateHiddenInput(this, "districtText");

        // Reset phường/xã về mặc định
        document.getElementById("ward").innerHTML = '<option value="">Chọn phường/xã</option>';

        // Reset input hidden của phường/xã
        document.getElementById("wardText").value = "";
    });

    // Khi chọn phường/xã
    document.getElementById("ward").addEventListener("change", function () {
        updateHiddenInput(this, "wardText");
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
<c:if test="${action != null}">
    <c:set var="action" value="${''}"/>
</c:if>