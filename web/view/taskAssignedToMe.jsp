<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="dto.*"%>
<%@page import="entity.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Task Assigned To Me Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <style>
            /* Căn giữa trang */
            .box {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            /* Layout chính */
            .tasks {
                display: grid;
                grid-template-rows: auto 1fr;
                gap: 5px;
                height: 100%;
                width: 100%;
                background-color: rgba(255, 255, 255, 0.9);
                overflow: hidden;
            }

            /* Header */
            .tasks__header {
                display: grid;
                grid-template-columns: repeat(5, 1fr);
                gap: 5px;
                padding: 10px;
                color: white;
                font-weight: bold;
                border-radius: 12px 12px 0 0;
            }

            .tasks__header-title {
                display: flex;
                justify-content: center;
                padding: 12px;
                border-radius: 12px;
                background: #1e293b;
                align-items: center;
                font-size: 1.8rem;
            }

            /* Body */
            .tasks__body {
                display: grid;
                grid-template-rows: repeat(8, 1fr);
                gap: 5px;
                padding: 10px;
            }

            /* Task */
            .task {
                display: grid;
                grid-template-columns: repeat(5, 1fr);
                gap: 5px;
                background: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
            }

            .task__item {
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 1.6rem;
                padding: 12px;
                background: #ffffff;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
            }

            .task__item:hover {
                transform: translateY(-3px);
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
            }

            /* Trạng thái */
            .task__status--canceled {
                background: #ff6600;
                color: white;
                font-weight: bold;
            }

            .task__status--completed {
                background: #00cc00;
                color: white;
                font-weight: bold;
            }

            /* Controls */
            .task__controls {
                display: flex;
                gap: 10px;
                justify-content: center;
                padding: 10px;
            }

            .task__control {
                padding: 8px 12px;
                font-size: 1.4rem;
                background: #4f46e5;
                color: white;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background 0.2s ease-in-out;
                outline: none;
            }

            .task__control:hover {
                background: #4338ca;
            }

            /* Overlay mờ nền */
            .tasks__modal {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5); /* Làm mờ nền */
                display: flex;
                justify-content: center;
                align-items: center;
                opacity: 0;
                visibility: hidden;
                transition: opacity 0.3s ease-in-out, visibility 0.3s ease-in-out;
            }

            /* Khi mở modal */
            .tasks__modal.active {
                opacity: 1;
                visibility: visible;
            }

            /* Khung modal */
            .modal {
                background: white;
                width: 400px;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                text-align: center;
                transform: translateY(-20px);
                transition: transform 0.3s ease-in-out;
            }

            /* Hiệu ứng xuất hiện khi mở */
            .tasks__modal.active .modal {
                transform: translateY(0);
            }

            /* Tiêu đề modal */
            .modal__heading {
                font-size: 2.4rem;
                font-weight: bold;
                margin-bottom: 10px;
            }

            /* Mô tả */
            .modal__description {
                margin-bottom: 15px;
                padding: 10px;
                border-radius: 8px;
                max-height: 100px;
                font-size: 1.8rem;
                background: #2f57ef;
                color: #fff;
                overflow: auto;
            }

            /* Thông tin */
            .modal__assigned-by,
            .modal__due-date {
                font-size: 1.6rem;
                color: #777;
                margin-bottom: 10px;
            }

            /* Điều khiển */
            .modal__controls {
                display: flex;
                width: 100%;
                justify-content: space-around;
                margin-top: 15px;
            }

            /* Nút bấm */
            .modal__control {
                width: 50px;
                height: 50px;
                font-size: 2rem;
                border: none;
                cursor: pointer;
                transition: transform 0.2s ease-in-out;
            }

            /* Nút hủy */
            .modal__control--canceled {
                background: #ff4d4d;
                color: white;
            }

            .modal__control--canceled:hover {
                background: #e63939;
                transform: scale(1.1);
            }

            /* Nút hoàn thành */
            .modal__control--completed {
                background: #4caf50;
                color: white;
            }

            .modal__control--completed:hover {
                background: #3e8e41;
                transform: scale(1.1);
            }

            /* Trạng thái */
            .modal__status {
                width: 100%;
                padding: 12px;
                font-size: 1.4rem;
                background: #4f46e5;
                color: white;
                border: none;
                border-radius: 6px;
                transition: background 0.2s ease-in-out;
                outline: none;
            }

            .modal__status--canceled {
                background: #ff6600;
            }

            .modal__status--canceled:hover {
                background: #e63939;
            }

            .modal__status--completed {
                background: #00cc00;
            }

            .modal__status--completed:hover {
                background: #00b300;
            }

            /* Responsive cho màn hình nhỏ hơn 768px (điện thoại) */
            @media (max-width: 768px) {
                .box {
                    height: auto;
                    padding: 20px;
                }

                .tasks {
                    grid-template-rows: auto;
                    width: 100%;
                }

                .tasks__header {
                    grid-template-columns: 1fr;
                    gap: 10px;
                }

                .tasks__header-title {
                    font-size: 1.4rem;
                    padding: 8px;
                }

                .tasks__body {
                    grid-template-rows: auto;
                    gap: 10px;
                }

                .task {
                    grid-template-columns: 1fr;
                    gap: 10px;
                    padding: 10px;
                }

                .task__item {
                    font-size: 1.4rem;
                    padding: 8px;
                }

                .modal {
                    width: 90%;
                    padding: 15px;
                }

                .modal__heading {
                    font-size: 2rem;
                }

                .modal__description {
                    font-size: 1.6rem;
                }

                .modal__assigned-by,
                .modal__due-date {
                    font-size: 1.4rem;
                }

                .modal__controls {
                    flex-direction: column;
                    gap: 10px;
                }

                .modal__control {
                    width: 100%;
                    height: auto;
                    font-size: 1.6rem;
                }
            }

            /* Responsive cho màn hình nhỏ hơn 1024px (máy tính bảng) */
            @media (max-width: 1024px) {
                .tasks__header {
                    grid-template-columns: repeat(3, 1fr);
                }

                .task {
                    grid-template-columns: repeat(3, 1fr);
                }

                .task__item {
                    font-size: 1.5rem;
                }

                .modal {
                    width: 70%;
                }

                .modal__heading {
                    font-size: 2.2rem;
                }

                .modal__description {
                    font-size: 1.6rem;
                }
            }
        </style>

        <%
            // Tạo các biến session
            session.setAttribute("clubId", 1);
            Member member = new Member.Builder().setId(0).setName("Hùng").build();
            session.setAttribute("member", member);
        %>

        <script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/dto/TaskAssignedToMe.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/dto/TaskAssignedByMe.js"></script>
        <script>
            // Kết nối WebSocket đến Server
            var socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/task");

            // Khi kết nối thành công
            socket.onopen = function () {
                console.log("Kết nối WebSocket thành công!");
            };

            function getTaskNode(taskAssignedToMe) {
                // Tạo phần tử div chính
                let taskNode = document.createElement("div");
                taskNode.className = "task";
                taskNode.setAttribute("data-task-id", taskAssignedToMe.taskId);
                taskNode.onclick = function () {
                    location.href = "'?taskId=" + taskAssignedToMe.taskId - 1 + "'";
                };

                // Tạo các phần tử con bên trong div
                taskNode.innerHTML = `
                    <div class="task__item">` + taskAssignedToMe.taskName + `</div>
                    <div class="task__item">` + taskAssignedToMe.taskDescription + `</div>
                    <div class="task__item">` + taskAssignedToMe.taskAssignedBy + `</div>
                    <div class="task__item task__status--in-progress">
                        <div class="task__controls">
                            <button class="task__control task__control--canceled" onclick="sendMessage(this)" data-task-id="` + taskAssignedToMe.taskId + `">❌</button>
                            <button class="task__control task__control--completed" onclick="sendMessage(this)" data-task-id="` + taskAssignedToMe.taskId + `">✔️</button>
                        </div>
                    </div>
                    <div class="task__item">` + taskAssignedToMe.taskDueDate + `</div>
                `;

                return taskNode;
            }

            function createTask(taskAssignedToMe) {
                let taskContainer = $(".tasks__body");
                taskContainer.appendChild(getTaskNode(taskAssignedToMe));
            }

            function editTask(taskAssignedToMe) {
                let taskContainer = $(".tasks__body");
                let task = taskContainer.querySelector(`.task[data-task-id="` + taskAssignedToMe.taskId + `"]`);
                console.log(`.task[data-task-id="` + taskAssignedToMe.taskId + `"]`);
                taskContainer.replaceChild(getTaskNode(taskAssignedToMe), task);
            }

            function deleteTask(taskAssignedToMe) {
                let taskContainer = $(".tasks__body");
                let task = taskContainer.querySelector(`.task[data-task-id="` + taskAssignedToMe.taskId + `"]`);
                taskContainer.removeChild(task);
            }

            function isTaskAssignedToMe(taskAssignedToMe) {
                console.log(taskAssignedToMe);
                console.log("${member.id}");
                return taskAssignedToMe.taskAssignedTo.includes("${member.id}") && taskAssignedToMe.clubId == "${clubId}";
            }

            // Khi nhận tin nhắn từ server
            socket.onmessage = function (event) {
                let message = JSON.parse(event.data);

                if (!isTaskAssignedToMe(message.data)) {
                    return;
                }

                console.log("Nhận tin nhắn từ Server: ", message);

                let actionHandlers = new Map([
                    ["createTask", createTask],
                    ["editTask", editTask],
                    ["deleteTask", deleteTask]
                ]);

                actionHandlers.get(message.action)(message.data);
            };

            // Khi xảy ra lỗi
            socket.onerror = function (error) {
                console.log("Lỗi WebSocket: " + error);
            };

            // Khi WebSocket bị đóng
            socket.onclose = function () {
                console.log("WebSocket bị đóng!");
            };

            // Gửi tin nhắn lên Server
            function sendMessage(element) {
                event.stopPropagation(); // Ngăn chặn sự kiện click lan ra ngoài
                let id = element.getAttribute("data-task-id");
                let status = element.textContent == "❌" ? "Hủy bỏ" : "Đã xong";
                let taskAssignedToMe = new TaskAssignedByMe(id, status);
                let message = {
                    action: "updateStatus",
                    data: taskAssignedToMe
                }
                socket.send(JSON.stringify(message));
            }
        </script>
    </head>
    <body>
        <%
            // Tạo danh sách task
            ArrayList<TaskAssignedToMeResponse> tasks = new ArrayList<>();

            // Thêm các task mẫu vào danh sách
            tasks.add(new TaskAssignedToMeResponse(1, "Fix Bug #101", "Sửa lỗi đăng nhập", new Member.Builder().setName("Alice").build(), "Đang làm", Timestamp.valueOf("2025-03-20 00:00:00")));
            tasks.add(new TaskAssignedToMeResponse(2, "Implement Feature X", "Thêm tính năng X vào hệ thống", new Member.Builder().setName("Charlie").build(), "Hủy bỏ", Timestamp.valueOf("2025-03-25 00:00:00")));
            tasks.add(new TaskAssignedToMeResponse(3, "Code Review", "Kiểm tra lại code module Y", new Member.Builder().setName("Eve").build(), "Đã xong", Timestamp.valueOf("2025-03-10 00:00:00")));
            tasks.add(new TaskAssignedToMeResponse(4, "Write Unit Tests", "Viết unit test cho module Z", new Member.Builder().setName("Grace").build(), "Đang làm", Timestamp.valueOf("2025-03-18 00:00:00")));
        
            pageContext.setAttribute("tasks", tasks);
            request.setAttribute("contentHeader", "Nhiệm vụ của tôi");
        %>
        <jsp:include page="contentHeader.jsp" />
        <div class="box">
            <div class="tasks">
                <div class="tasks__header">
                    <div class="tasks__header-title">Nhiệm vụ</div>
                    <div class="tasks__header-title">Ghi chú</div>
                    <div class="tasks__header-title">Giao bởi</div>
                    <div class="tasks__header-title">Trạng thái</div>
                    <div class="tasks__header-title">Hạn chót</div>
                </div>
                <div class="tasks__body">
                    <c:forEach items="${tasks}" var="task">
                    <div class="task" onclick="location.href='?taskId=${task.id - 1}'" data-task-id="${task.id}">
                        <div class="task__item">${task.name}</div>
                        <div class="task__item">${task.description}</div>
                        <div class="task__item">${task.assignedByMember.name}</div>
                        <c:choose>
                            <c:when test="${task.status == 'Đang làm'}">
                                <div class="task__item task__status--in-progress">
                                    <div class="task__controls">
                                        <button class="task__control task__control--canceled" onclick="sendMessage(this)" data-task-id="${task.id}">❌</button>
                                        <button class="task__control task__control--completed" onclick="sendMessage(this)" data-task-id="${task.id}">✔️</button>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${task.status == 'Hủy bỏ'}">
                                <div class="task__item task__status--canceled">${task.status}</div>
                            </c:when>
                            <c:when test="${task.status == 'Đã xong'}">
                                <div class="task__item task__status--completed">${task.status}</div>
                            </c:when>
                        </c:choose>
                        <div class="task__item">${task.dueDate}</div>
                    </div>
                    </c:forEach>
                </div>
                <div class="tasks__modal ${param.taskId != null ? 'active' : ''}" onclick="location.href='?'">
                    <c:set var="currentTask" value="${tasks.get(param.taskId)}"/>
                    <div class="modal" onclick="event.stopPropagation()">
                        <div class="modal__heading">${currentTask.name}</div>
                        <div class="modal__description">Nội dung: ${currentTask.description}</div>
                        <div class="modal__assigned-by">Giao bởi: ${currentTask.assignedByMember.name}</div>
                        <div class="modal__due-date">Hạn chót: ${currentTask.dueDate}</div>
                        <div class="modal__controls">
                            <c:choose>
                                <c:when test="${currentTask.status == 'Đang làm'}">
                                    <button class="modal__control modal__control--canceled" onclick="sendMessage(this)" data-task-id="${task.id}">❌</button>
                                    <button class="modal__control modal__control--completed" onclick="sendMessage(this)" data-task-id="${task.id}">✔️</button>
                                </c:when>
                                <c:when test="${currentTask.status == 'Hủy bỏ'}">
                                    <button class="modal__status modal__status--canceled">${currentTask.status}</button>
                                </c:when>
                                <c:when test="${currentTask.status == 'Đã xong'}">
                                    <button class="modal__status modal__status--completed">${currentTask.status}</button>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
        </script>
    </body>
</html>
