<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.Date"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Task Assigned By Me Page</title>
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

            .multiselect-dropdown .optexts {
                height: 60px;
                overflow: auto;
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
        </style>
    </head>
    <body>
        <%
            // Tạo danh sách task
            ArrayList<Task> tasks = new ArrayList<>();

            // Thêm các task mẫu vào danh sách
            tasks.add(new Task(1, "Fix Bug #101", "Sửa lỗi đăng nhập", "Alice", "Bob", "Đang làm", Date.valueOf("2025-03-20")));
            tasks.add(new Task(2, "Implement Feature X", "Thêm tính năng X vào hệ thống", "Charlie", "Dave", "Hủy bỏ", Date.valueOf("2025-03-25")));
            tasks.add(new Task(3, "Code Review", "Kiểm tra lại code module Y", "Eve", "Frank", "Đã xong", Date.valueOf("2025-03-10")));
            tasks.add(new Task(4, "Write Unit Tests", "Viết unit test cho module Z", "Grace", "Hannah", "Đang làm", Date.valueOf("2025-03-18")));
        
            pageContext.setAttribute("tasks", tasks);
            request.setAttribute("contentHeader", "Giao việc");
        %>
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
                    <div class="task">
                        <input type="text" class="task__item" value="${task.name}">
                        <textarea class="task__item">${task.description}</textarea>
                        <div class="task__item">
                            <select name="assignedTo" id="task__assigned-to"  multiple multiselect-search="true" multiselect-select-all="true" multiple-select-hide-x="true">
                                <option value="0" ${task.assignedTo == "Bob" ? "selected" : ""}>Bob</option>
                                <option value="1" ${task.assignedTo == "Dave" ? "selected" : ""}>Dave</option>
                                <option value="2" ${task.assignedTo == "Frank" ? "selected" : ""}>Frank</option>
                                <option value="3" ${task.assignedTo == "Hannah" ? "selected" : ""}>Hannah</option>
                            </select>
                        </div>
                        <c:choose>
                            <c:when test="${task.status == 'Đang làm'}">
                                <div class="task__item task__status--in-progress">${task.status}</div>
                            </c:when>
                            <c:when test="${task.status == 'Hủy bỏ'}">
                                <div class="task__item task__status--canceled">${task.status}</div>
                            </c:when>
                            <c:when test="${task.status == 'Đã xong'}">
                                <div class="task__item task__status--completed">${task.status}</div>
                            </c:when>
                        </c:choose>
                        <input type="date" class="task__item" value="${task.dueDate}">
                        <div class="task__controls">
                            <button class="task__control task__control--edit">Lưu</button>
                            <button class="task__control task__control--delete">Xóa</button>
                        </div>
                    </div>
                    </c:forEach>
                    <div class="task">
                        <input type="text" class="task__item" placeholder="Tên công việc">
                        <textarea class="task__item" placeholder="Mô tả công việc"></textarea>
                        <div class="task__item task__assigned-to">
                            <select name="assignedTo" id="task__assigned-to" multiple multiselect-search="true" multiselect-select-all="true">
                                <option value="0">Bob</option>
                                <option value="1">Dave</option>
                                <option value="2">Frank</option>
                                <option value="3">Hannah</option>
                            </select>
                        </div>
                        <div class="task__item task__status--in-progress">Đang làm</div>
                        <input type="date" class="task__item">
                        <div class="task__controls">
                            <button class="task__control">Thêm</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/js/multiselect-dropdown/multiselect-dropdown.js"></script>
    </body>
</html>
