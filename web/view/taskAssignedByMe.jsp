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
        <title>Task Assigned By Me Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/library/multi-select-dropdown/MultiSelect.css">
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
                overflow: auto;
            }

            /* Header */
            .tasks__header {
                display: grid;
                grid-template-columns: repeat(6, 1fr);
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
                display: flex;
                flex-direction: column;
                gap: 5px;
                padding: 10px;
            }

            /* Task */
            .task {
                flex: 0 0 8.3333333333%;
                display: grid;
                grid-template-columns: repeat(6, 1fr);
                gap: 5px;
                background: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .task__item {
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 1.6rem;
                padding: 12px;
                background: #ffffff;
                border-radius: 8px;
                border: none;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                outline: none;
            }

            #task__assigned-to {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 8px;
                font-size: 1.6rem;
                background: #f9f9f9;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
            }

            .time-picker {
                text-align: center;
            }

            /* Trạng thái task */
            .task__status--in-progress {
                background: #ffcc00;
                color: white;
                font-weight: bold;
            }

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
                width: 100%;
                font-size: 1.4rem;
                background: #2f57ef;
                color: white;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background 0.2s ease-in-out;
                outline: none;
            }

            .task__control:hover {
                opacity: 0.9;
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
                    text-align: center;
                }

                .tasks__body {
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
                    text-align: center;
                }

                .task__controls {
                    flex-direction: column;
                    gap: 10px;
                }

                .task__control {
                    width: 100%;
                    font-size: 1.4rem;
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

                .task__controls {
                    gap: 5px;
                }

                .task__control {
                    font-size: 1.4rem;
                }
            }

            .multi-select-header {
                height: 6.4rem;
                width: 100%;
                overflow: auto;
            }

            .multi-select-header::after {
                display: none;
            }

            .multi-select-header-option {
                font-size: 1.6rem !important;
            }
        </style>

        
        <%
            // Tạo các biến session
            Member hung = new Member.Builder().setId(0).setName("Hùng").build();
            Member hieu = new Member.Builder().setId(1).setName("Hiếu").build();
            Member hoa = new Member.Builder().setId(2).setName("Hòa").build();
            Member minh = new Member.Builder().setId(3).setName("Minh").build();
            Member trang = new Member.Builder().setId(4).setName("Trang").build();
            Member linh = new Member.Builder().setId(5).setName("Linh").build();
            Member quang = new Member.Builder().setId(6).setName("Quang").build();
            Member thao = new Member.Builder().setId(7).setName("Thảo").build();
            Member tuan = new Member.Builder().setId(8).setName("Tuấn").build();
            Member anh = new Member.Builder().setId(9).setName("Anh").build();
            Member bao = new Member.Builder().setId(10).setName("Bảo").build();
            Member chi = new Member.Builder().setId(11).setName("Chi").build();
            Member dung = new Member.Builder().setId(12).setName("Dũng").build();
            Member giang = new Member.Builder().setId(13).setName("Giang").build();
            Member khanh = new Member.Builder().setId(14).setName("Khánh").build();
            Member phuong = new Member.Builder().setId(15).setName("Phương").build();

            session.setAttribute("member", hieu);

            // Tạo danh sách member
            ArrayList<Member> members = new ArrayList<>();
            members.add(hung);
            members.add(hieu);
            members.add(hoa);
            members.add(minh);
            members.add(trang);
            members.add(linh);
            members.add(quang);
            members.add(thao);
            members.add(tuan);
            members.add(anh);
            members.add(bao);
            members.add(chi);
            members.add(dung);
            members.add(giang);
            members.add(khanh);
            members.add(phuong);

            pageContext.setAttribute("members", members);

            // Tạo danh danh sách thành viên được giao việc
            ArrayList<Integer> assignedTos = new ArrayList<>();
            assignedTos.add(0);

            // Tạo danh sách task
            ArrayList<TaskAssignedByMeResponse> tasks = new ArrayList<>();

            // Thêm các task mẫu vào danh sácsh
            tasks.add(new TaskAssignedByMeResponse(1, "Fix Bug #101", "Sửa lỗi đăng nhập", assignedTos, "Đang làm", Timestamp.valueOf("2025-03-20 00:00:00")));
            tasks.add(new TaskAssignedByMeResponse(2, "Implement Feature X", "Thêm tính năng X vào hệ thống", assignedTos, "Hủy bỏ", Timestamp.valueOf("2025-03-25 00:00:00")));
            tasks.add(new TaskAssignedByMeResponse(3, "Code Review", "Kiểm tra lại code module Y", assignedTos, "Đã xong", Timestamp.valueOf("2025-03-10 00:00:00")));
            tasks.add(new TaskAssignedByMeResponse(4, "Write Unit Tests", "Viết unit test cho module Z", assignedTos, "Đang làm", Timestamp.valueOf("2025-03-18 00:00:00")));
        
            pageContext.setAttribute("tasks", tasks);
            request.setAttribute("contentHeader", "Giao việc");
        %>

        <script src="${pageContext.request.contextPath}/assets/js/dto/TaskAssignedByMe.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/dto/TaskAssignedToMe.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
        <script>
            // Kết nối WebSocket đến Server
            var socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/task");

            // Khi kết nối thành công
            socket.onopen = function () {
                console.log("Kết nối WebSocket thành công!");
            };

            function updateStatus(taskAssignedByMe) {
                let task = $(".task__item.task__status[data-task-id='" + taskAssignedByMe.taskId + "']");

                if (task) {
                    task.textContent = taskAssignedByMe.status;
                    task.classList.remove("task__status--in-progress");
                    task.classList.remove("task__status--canceled");
                    task.classList.remove("task__status--completed");

                    if (taskAssignedByMe.status === "Đang làm") {
                        task.classList.add("task__status--in-progress");
                    } else if (taskAssignedByMe.status === "Hủy bỏ") {
                        task.classList.add("task__status--canceled");
                    } else if (taskAssignedByMe.status === "Đã xong") {
                        task.classList.add("task__status--completed");
                    }
                }
            }

            // Khi nhận tin nhắn từ server
            socket.onmessage = function (event) {
                let message = JSON.parse(event.data);
                console.log("Nhận tin nhắn từ Server: ", message);
                if (message.action === "updateStatus") {
                    updateStatus(message.data);
                }
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
            function sendMessage(element, action) {
                let task = element.closest(".task");
                let taskAttributes = task.querySelectorAll(".task__item");
                let taskId = task.getAttribute("data-task-id");
                let taskName = taskAttributes[0].value;
                let taskDescription = taskAttributes[1].value;
                let taskAssignedTo = [...taskAttributes[2].querySelectorAll('input[type="hidden"]')].map(option => option.value);
                let taskStatus = taskAttributes[3].textContent;
                let taskDueDate = taskAttributes[4].value;

                let taskAssignedToMe = new TaskAssignedToMe(taskId, taskName, taskDescription, "${member.name}", taskAssignedTo, taskStatus, taskDueDate, ${clubId});

                let message = {
                    action: action,
                    data: taskAssignedToMe,
                };

                socket.send(JSON.stringify(message));
            }
        </script>
    </head>
    <body>
        <jsp:include page="contentHeader.jsp" />
        <div class="box">
            <div class="tasks">
                <div class="tasks__header">
                    <div class="tasks__header-title">Nhiệm vụ</div>
                    <div class="tasks__header-title">Ghi chú</div>
                    <div class="tasks__header-title">Giao cho</div>
                    <div class="tasks__header-title">Trạng thái</div>
                    <div class="tasks__header-title">Hạn chót</div>
                    <div class="tasks__header-title">Chức năng</div>
                </div>
                <div class="tasks__body">
                    <c:forEach items="${tasks}" var="task">
                    <div class="task" data-task-id="${task.id}">
                        <input type="text" class="task__item" value="${task.name}" required>
                        <textarea class="task__item">${task.description}</textarea>
                        <div class="task__item">
                            <select id="task__assigned-to" data-placeholder="Chọn thành viên" data-search="true" data-select-all="true" data-list-all="true" multiple data-multi-select>
                                <c:forEach items="${members}" var="member">
                                    <option value="${member.id}" ${task.isMemberInAssignedMembers(member.id) ? "selected" : ""}>${member.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <c:choose>
                            <c:when test="${task.status == 'Đang làm'}">
                                <div class="task__item task__status task__status--in-progress" data-task-id="${task.id}">${task.status}</div>
                            </c:when>
                            <c:when test="${task.status == 'Hủy bỏ'}">
                                <div class="task__item task__status task__status--canceled" data-task-id="${task.id}">${task.status}</div>
                            </c:when>
                            <c:when test="${task.status == 'Đã xong'}">
                                <div class="task__item task__status task__status--completed" data-task-id="${task.id}">${task.status}</div>
                            </c:when>
                        </c:choose>
                        <input class="task__item time-picker" type="text" value="${task.dueDate}" required>
                        <div class="task__controls">
                            <button class="task__control task__control--edit" onclick="sendMessage(this, 'editTask')">Lưu</button>
                            <button class="task__control task__control--delete" onclick="sendMessage(this, 'deleteTask')">Xóa</button>
                        </div>
                    </div>
                    </c:forEach>
                    <form action="${pageContext.request.contextPath}/TaskAssignedByMeServlet" method="post">
                        <div class="task">
                            <input type="text" class="task__item" placeholder="Tên công việc" name="taskName" required>
                            <textarea class="task__item" placeholder="Mô tả công việc" name="taskDescription"></textarea>
                            <div class="task__item task__assigned-to">
                                <select name="taskAssignedTo" id="task__assigned-to" data-placeholder="Chọn thành viên" data-search="true" data-select-all="true" data-list-all="true" multiple data-multi-select required>
                                    <c:forEach items="${members}" var="member">
                                        <option value="${member.id}">${member.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="task__item task__status task__status--in-progress" name="taskStatus">Đang làm</div>
                            <input class="task__item time-picker" type="text" name="taskDueDate" required>
                            <div class="task__controls">
                                <button class="task__control" onclick="sendMessage(this, 'createTask')">Thêm</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/library/multi-select-dropdown/MultiSelect.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
        <script>
            // Kích hoạt Flatpickr cho trường nhập ngày giờ
            flatpickr(".time-picker", {
                enableTime: true,
                enableSeconds: true,
                dateFormat: "H:i:S d-m-Y",
                time_24hr: true,
            });
        </script>

        <script>
            // Kiểm tra xem có phải ở chế độ tạo nhiệm hay không
            let action = "${action}";
            if (action === "createTask") {
                let task = document.querySelector(".messages .task:last-child");
                sendMessage(task, action);
            }
        </script>

        <c:if test="${action != null}">
            <c:set var="action" value="${''}"/>
        </c:if>
    </body>
</html>
