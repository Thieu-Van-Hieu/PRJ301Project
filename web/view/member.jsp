<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/member-css/member.css" />
<div class="content-container">
    <header class="content-header">
        <h2>Thành Viên</h2>
        <div class="content-header_util">
            <i class="fa-solid fa-scroll" onclick="location.href = '${pageContext.request.contextPath}/RequestClubServlet'"></i>
            <i class="fa-solid fa-user-group" onclick="location.href = '${pageContext.request.contextPath}/MemberServlet'"></i>
        </div>
    </header>
    <jsp:include page="noticeError.jsp"/>
    <jsp:include page="noticeSuccess.jsp"/>
    <div class="content-body">
        <div class="content-filter">
            <form action="${pageContext.request.contextPath}/filtermember" method="get">
                <table>
                    <tr>
                        <td><select name="department" id="">
                                <option value="1">Văn Hoá</option>
                                <option value="2">Kinh Tế</option>
                                <option value="3">Hậu Cần</option>
                                <option value="4">Truyền Thông</option>
                                <option value="5">Chuyên Môn</option>
                            </select>
                        </td>
                        <td>
                            <select name="gender" id="">
                                <option value="male">Nam</option>
                                <option value="female">Nữ</option>
                            </select>
                        </td>
                        <td class="filter-search"><input type="text" placeholder="Name, id, email" name="search"></td>
                    </tr>
                    <tr>
                        <td class="age-filter">Age <input type="text" name="ageFrom"> - <input type="text" name="ageTo">
                            year(s)</td>
                        <td>
                        </td>
                        <td class="filter-submit"><input type="submit" value="Filter"></td>
                    </tr>
                </table>
            </form>
        </div>


        <div class="content-member-information">
            <table border="1">
                <tr>
                    <th>Mã sinh viên</th>
                    <th>Họ</th>
                    <th>Tên</th>
                    <th>Ngày sinh</th>
                    <th>Giới Tính</th>
                    <th>Phòng Ban</th>
                    <th>Chức vụ</th>
                    <th></th>
                </tr>

                <c:if test="${empty filterMemberResponseDTO}">
                    <c:forEach var="x" items="${memberResponses}">
                        <tr>
                            <td>${x.rollNumber}</td> 
                            <td>${x.lastName}</td> 
                            <td>${x.firstName}</td> 
                            <td>${x.birthday}</td> 
                            <td>${x.gender}</td> 
                            <td>${x.del}</td> 
                            <td>${x.role}</td> 
                            <c:if test="${member.role eq 'Chủ Nhiệm'}"> 
                                <td><button value="Delete" onclick="location.href = '${pageContext.request.contextPath}/MemberServlet?action=delete&rollNumber=${x.rollNumber}'">Xoá</button></td> 
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>

                <c:if test="${not empty filterMemberResponseDTO}">
                    <c:forEach var="x" items="${filterMemberResponseDTO}">
                        <tr>
                            <td>${x.studentID}</td> 
                            <td>${x.lastName}</td> 
                            <td>${x.firstName}</td> 
                            <td>${x.birthdate}</td> 
                            <td>${x.gender}</td> 
                            <td>${x.department}</td> 
                            <td>${x.roleClub}</td> 
                            <c:if test="${member.role eq 'Chủ Nhiệm'}"> 
                                <td><button value="Delete" onclick="location.href = '${pageContext.request.contextPath}/MemberServlet?action=delete&rollNumber=${x.studentID}'">Xoá</button></td> 
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/effect-js/roihoa.js"></script>

</html>
