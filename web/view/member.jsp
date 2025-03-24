<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/member-css/member.css" />
<div class="content-container">
    <header class="content-header">
        <h2>Thành Viên</h2>
        <div class="content-header_util">
            <c:if test="${member.role eq 'Chủ Nhiệm'}">
                <i class="fa-solid fa-scroll" onclick="location.href = '${pageContext.request.contextPath}/RequestClubServlet'"></i>
            </c:if>
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
                                <option value="6">Quản Lý</option>
                            </select>
                        </td>
                        <td>
                            <select name="gender" id="">
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </select>
                        </td>
                        <td class="filter-search"><input type="text" placeholder="Name, id, email" name="search"></td>
                    </tr>
                    <tr>
                        <td class="age-filter">
                            Age 
                            <input type="text" name="ageFrom"> 
                            <span class="error" style="color:red; font-weight: 700;"></span> - 
                            <input type="text" name="ageTo"> 
                            <span class="error" style="color:red; font-weight: 700;"></span> year(s)
                        </td>
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
                        <c:if test="${member.role eq 'Chủ Nhiệm'}"> 
                        <th></th>
                        </c:if>
                </tr>
                <c:forEach var="x" items="${memberResponses}">
                    <tr>
                        <td>${x.rollNumber}</td> 
                        <td>${x.lastName}</td> 
                        <td>${x.firstName}</td> 
                        <td>${x.birthday}</td> 
                        <td>${x.gender}</td> 
                        <td>${x.del}</td> 
                        <td>${x.role}</td> 
                        <c:if test="${member.role eq 'Chủ Nhiệm' && x.role ne 'Chủ Nhiệm'}"> 
                            <td><button value="Delete" onclick="location.href = '${pageContext.request.contextPath}/MemberServlet?action=delete&memberId=${x.memberId}&clubID=${x.clubid}'">Xoá</button></td> 
                        </c:if>
                    </tr>
                </c:forEach>
                <c:forEach var="x" items="${filterMemberResponseDTO}">
                    <tr>
                        <td>${x.studentID}</td> 
                        <td>${x.lastName}</td> 
                        <td>${x.firstName}</td> 
                        <td>${x.birthdate}</td> 
                        <td>${x.gender}</td> 
                        <td>${x.department}</td> 
                        <td>${x.roleClub}</td> 
                        <c:if test="${member.role eq 'Chủ Nhiệm' && x.roleClub ne 'Chủ Nhiệm'}"> 
                            <td><button value="Delete" onclick="location.href = '${pageContext.request.contextPath}/MemberServlet?action=delete&memberId=${x.memberId}&clubID=${x.clubId}'">Xoá</button></td> 
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/effect-js/roihoa.js"></script>
<script>
                                function validateAge(input, errorSpan) {
                                    const value = input.value.trim();
                                    // Nếu ô nhập rỗng thì không kiểm tra, không hiển thị lỗi
                                    if (value === "") {
                                        input.style.border = "";
                                        errorSpan.textContent = "";
                                        return;
                                    }
                                    const num = Number(value);
                                    // Kiểm tra nếu không phải số hoặc số <= 0 thì hiển thị thông báo lỗi
                                    if (isNaN(num) || num <= 0) {
                                        input.style.border = "2px solid red";
                                        errorSpan.textContent = "Không được nhập số bé hơn 0";
                                    } else {
                                        input.style.border = "";
                                        errorSpan.textContent = "";
                                    }
                                }

                                document.addEventListener("DOMContentLoaded", function () {
                                    const ageFrom = document.querySelector("input[name='ageFrom']");
                                    const ageTo = document.querySelector("input[name='ageTo']");

                                    // Giả sử thẻ <span> ngay sau input là thông báo lỗi
                                    const errorFrom = ageFrom.nextElementSibling;
                                    const errorTo = ageTo.nextElementSibling;

                                    ageFrom.addEventListener("input", function () {
                                        validateAge(ageFrom, errorFrom);
                                    });
                                    ageTo.addEventListener("input", function () {
                                        validateAge(ageTo, errorTo);
                                    });
                                });
</script>
</html>
