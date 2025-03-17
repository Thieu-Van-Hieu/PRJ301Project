<%@page contentType="text/html;charset=UTF-8" language="java"%>

<div class="content-container">
    <header class="content-header">
        <h2>Thành Viên</h2>
        <div class="content-header_util">
            <i class="fa-solid fa-scroll"></i>
            <i class="fa-solid fa-user-group"></i>
        </div>
    </header>

    <div class="content-body">
        <div class="content-filter">
            <form action="#" method="GET">
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
                        <td class="filter-time">Thời điểm tham gia<input type="date"></td>
                        <td>
                            <select name="gender" id="">
                                <option value="1">Nam</option>
                                <option value="2">Nữ</option>
                                <option value="3">Giới Tính Khác</option>
                            </select>
                        </td>
                        <td class="filter-search"><input type="text" placeholder="Name, id, email"></td>
                    </tr>
                    <tr>
                        <td class="age-filter">Age <input type="text" name="ageMin"> - <input type="text" name="ageMax">
                            year(s)</td>
                        <td>
                            <div class="status">
                                <div class="status-1">Tham gia hoạt động</div>
                                <div class="status-2">
                                    <div class="status-name"><input type="radio" name="status" value="1"> Chăm
                                    </div>
                                    <div class="status-name"><input type="radio" name="status" value="2"> Lười
                                        chẩy thây</div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <select name="firstName" id="">
                                <option value="1">Tên (A-Z)</option>
                                <option value="2">Tên (Z-A)</option>
                                <option value="1">Họ (A-Z)</option>
                                <option value="2">Họ (Z-A)</option>
                            </select>
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
                <tr>
                    <td>HE120204</td>
                    <td>Hầu</td>
                    <td>Hoà Inu</td>
                    <td>12/12/2004</td>
                    <td>Bê Đê</td>
                    <td>Kinh tế</td>
                    <td>Sai vặt</td>
                    <td><button value="Delete">Xoá</button></td>
                </tr>
            </table>
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
</script>

<script src="${pageContext.request.contextPath}/assets/js/effect-js/roihoa.js"></script>

</html>