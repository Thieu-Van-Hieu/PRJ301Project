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
                position: relative;
                flex: 0 0 8.3333333333%;
                display: grid;
                grid-template-columns: repeat(6, 1fr);
                gap: 5px;
                background: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .task--disabled::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(0, 0, 0, 0.5);
                border-radius: 8px;
                z-index: 9999;
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

            <%-- #task__assigned-to {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 8px;
                font-size: 1.6rem;
                background: #f9f9f9;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
            } --%>

            .time-picker {
                text-align: center;
            }

            /* Trạng thái task */
            .task__status--in-progress {
                background: #ffcc00;
                color: white;
                font-weight: bold;
            }

            .task__status--overdue {
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
            request.setAttribute("contentHeader", "Giao việc");
        %>

        <script src="${pageContext.request.contextPath}/assets/js/dto/TaskAssignedByMe.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/dto/TaskAssignedToMe.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
        <script>
            if ("${error}" !== "") {
                alert("${error}");
            }

            // Kết nối WebSocket đến Server
            var socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/task");

            // Khi kết nối thành công
            socket.onopen = function () {
                console.log("Kết nối WebSocket thành công!");
                let action = "${action}";
                if (action === "createTask") {
                    let task = document.querySelector(".tasks__body .task[data-task-id='${taskId}']");
                    sendMessage(task, action);
                }
            };

            function updateStatus(taskAssignedByMe) {
                let task = $(".task__item.task__status[data-task-id='" + taskAssignedByMe.taskId + "']");

                if (task) {
                    task.textContent = taskAssignedByMe.status;
                    task.classList.remove("task__status--in-progress");
                    task.classList.remove("task__status--overdue");
                    task.classList.remove("task__status--completed");

                    if (taskAssignedByMe.status === "Đang làm") {
                        task.classList.add("task__status--in-progress");
                    } else if (taskAssignedByMe.status === "Quá hạn") {
                        task.classList.add("task__status--overdue");
                    } else if (taskAssignedByMe.status === "Đã xong") {
                        task.classList.add("task__status--completed");
                        task.classList.add("task--disabled");
                    }
                }
            }

            function isSentToMe(taskAssignedByMe) {
                return taskAssignedByMe.assignedBy == ${member.id} && taskAssignedByMe.clubId == ${member.clubId};
            }

            // Khi nhận tin nhắn từ server
            socket.onmessage = function (event) {
                let message = JSON.parse(event.data);
                console.log("Nhận tin nhắn từ Server: ", message);

                if (!isSentToMe(message.data)) {
                    return;
                }

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
                event.preventDefault();
                let task = element.closest(".task");
                let taskAttributes = task.querySelectorAll(".task__item");
                let taskId = task.getAttribute("data-task-id");
                let taskName = taskAttributes[0].value;
                let taskDescription = taskAttributes[1].value;
                let taskAssignedTo = [...taskAttributes[2].querySelectorAll('input[type="hidden"]')].map(option => option.value);
                let taskStatus = taskAttributes[3].textContent;
                let taskDueDate = taskAttributes[4].value;

                let taskAssignedToMe = new TaskAssignedToMe(taskId, taskName, taskDescription, "${userInformation.firstName}", taskAssignedTo, taskStatus, taskDueDate, ${member.clubId});

                let message = {
                    action: action,
                    data: taskAssignedToMe,
                };

                socket.send(JSON.stringify(message));
                
                if (action === "editTask") {
                    task.querySelector("input[name='action']").value = "editTask";
                    task.closest("form").submit();
                } else if (action === "deleteTask") {
                    task.querySelector("input[name='action']").value = "deleteTask";
                    task.closest("form").submit();
                }
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
                    <form action="${pageContext.request.contextPath}/TaskAssignedByMeServlet" method="post">
                        <div class="task ${task.status != "Đang làm" ? "task--disabled" : ""}" data-task-id="${task.id}">
                            <input type="hidden" name="action" value="">
                            <input type="hidden" name="taskId" value="${task.id}">
                            <input type="text" class="task__item" value="${task.name}" name="taskName" required>
                            <textarea class="task__item" name="taskDescription">${task.description}</textarea>
                            <div class="task__item">
                                <select class="task__assigned-to" name="taskAssignedTo" data-placeholder="Chọn thành viên" data-search="true" data-select-all="true" data-list-all="true" multiple data-multi-select>
                                    <c:forEach items="${members}" var="member">
                                        <option value="${member.id}" ${task.isMemberInAssignedMembers(member.id) ? "selected" : ""}>${member.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <c:choose>
                                <c:when test="${task.status == 'Đang làm'}">
                                    <div class="task__item task__status task__status--in-progress" data-task-id="${task.id}">${task.status}</div>
                                    <input type="hidden" name="taskStatus" value="Đang làm">
                                </c:when>
                                <c:when test="${task.status == 'Quá hạn'}">
                                    <div class="task__item task__status task__status--overdue" data-task-id="${task.id}">${task.status}</div>
                                    <input type="hidden" name="taskStatus" value="Quá hạn">
                                </c:when>
                                <c:when test="${task.status == 'Đã xong'}">
                                    <div class="task__item task__status task__status--completed" data-task-id="${task.id}">${task.status}</div>
                                    <input type="hidden" name="taskStatus" value="Đã xong">
                                </c:when>
                            </c:choose>
                            <input class="task__item time-picker" type="text" value="${task.dueDate}" name="taskDueDate" required>
                            <div class="task__controls">
                                <input type="hidden" name="taskId" value="${task.id}">
                                <button class="task__control task__control--edit" onclick="sendMessage(this, 'editTask')">Lưu</button>
                                <button class="task__control task__control--delete" onclick="sendMessage(this, 'deleteTask')">Xóa</button>
                            </div>
                        </div>
                    </form>
                    </c:forEach>
                    <form action="${pageContext.request.contextPath}/TaskAssignedByMeServlet" method="post">
                        <div class="task">
                            <input type="hidden" name="action" value="createTask">
                            <input type="text" class="task__item" placeholder="Tên công việc" name="taskName" required>
                            <textarea class="task__item" placeholder="Mô tả công việc" name="taskDescription"></textarea>
                            <div class="task__item">
                                <select name="taskAssignedTo" class="task__assigned-to" data-placeholder="Chọn thành viên" data-search="true" data-select-all="true" data-list-all="true" multiple data-multi-select required>
                                    <c:forEach items="${members}" var="member">
                                        <option value="${member.id}">${member.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" name="taskStatus" value="Đang làm">
                            <div class="task__item task__status task__status--in-progress">Đang làm</div>
                            <input class="task__item time-picker" type="text" name="taskDueDate" required>
                            <div class="task__controls">
                                <button class="task__control">Thêm</button>
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
                dateFormat: "Y-m-d H:i:S",
                time_24hr: true,
            });

            // Kiểm tra thời gian đã hết chưa
            function checkDueDate() {
                let tasks = document.querySelectorAll(".task[data-task-id]");
                tasks.forEach(task => {
                    let taskDueDate = task.querySelector(".task__item.time-picker").value;
                    let taskStatus = task.querySelector(".task__item.task__status");
                    let now = new Date();
                    let dueDate = new Date(taskDueDate);
                    if (now > dueDate && taskStatus.textContent === "Đang làm") {
                        taskStatus.textContent = "Quá hạn";
                        task.querySelector("input[name='taskStatus']").value = "Quá hạn";
                        console.log("Quá hạn");
                        task.querySelector(".task__control.task__control--edit").click();
                    }
                });
            }

            // Kiểm tra thời gian đã hết chưa mỗi 1 giây
            setInterval(checkDueDate, 1000);
        </script>

        <c:if test="${action != null}">
            <c:set var="action" value="${''}"/>
        </c:if>
        <c:if test="${error != null}">
            <c:set var="error" value="${''}"/>
        </c:if>
    </body>
</html>
