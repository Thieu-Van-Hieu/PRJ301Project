<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="dto.RequestClubResponse" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/member-css/member.css" />
<div class="content-container">
    <header class="content-header">
        <h2>Danh sách yêu cầu vào câu lạc bộ</h2>
        <div class="content-header_util">
            <i class="fa-solid fa-scroll"></i>
            <i class="fa-solid fa-user-group" onclick="location.href='clubManagement'"></i>
        </div>
    </header>

    <div class="content-body">
        <div class="content-member-information">
            <c:if test="${requests == null || empty requests}">
            <div class="alert alert-info">
                Hiện tại không có yêu cầu nào đang chờ xử lý.
            </div>
            </c:if>

            <c:if test="${requests != null && !empty requests}">
            <table border="1">
                <tr>
                <th>Student ID</th>
                <th>Tên người dùng</th>
                <th>Thao tác</th>
                </tr>
                <c:forEach items="${requests}" var="request">
                <tr>
                    <td>${request.studentId}</td>
                    <td>${request.name}</td>
                    <td>
                    <form action="acceptRequest" method="POST" style="display: inline;">
                        <input type="hidden" name="requestId" value="${request.id}">
                        <button type="submit" class="btn btn-success btn-sm">
                        Chấp nhận
                        </button>
                    </form>
                    <form action="rejectRequest" method="POST" style="display: inline;">
                        <input type="hidden" name="requestId" value="${request.id}">
                        <button type="submit" class="btn btn-danger btn-sm">
                        Từ chối
                        </button>
                    </form>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:if>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/effect-js/roihoa.js"></script>
