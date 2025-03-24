<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
    <style>
        /* styles.css */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        h1 {
            text-align: center;
        }

        .close {
            width: 42px;
            height: 42px;
            border: 1px solid #ccc;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 50%;
            position: absolute;
            z-index: 1000;
            top: 24px;
            right: 24px;
            color: #ccc;
            cursor: pointer;
        }

        .Reload {
            border: none;
            text-align: center;
            width: 20%;
            background-color: #3D2097;
            color: #fff;
            padding: 12px 24px;
            border-radius: 16px;
            font-size: 12px;
            transition: 0.3s ease-in-out;
            cursor: pointer;
            font-weight: 700
        }

        .Reload:hover {
            border: 2px solid #DDDAD6;
            background-color: #7A66B7;
            color: #3D2097;
        }

        .card {
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background: #fff;
        }

        h2 {
            margin-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table,
        th,
        td {
            border: 1px solid #ddd;
        }

        th,
        td {
            padding: 10px;
            text-align: left;
        }

        button {
            padding: 5px 10px;
            border: none;
            cursor: pointer;
            background: #007bff;
            color: white;
            border-radius: 5px;
        }

        button.delete {
            background: #dc3545;
        }
    </style>
    <jsp:include page="sakura.jsp" />
    <jsp:include page="background.jsp" />
    <jsp:include page="/view/noticeSuccess.jsp" />
    <jsp:include page="/view/noticeError.jsp" />
    <jsp:include page="/view/background.jsp" />
    <div class="container">
        <h1>Admin Dashboard</h1>
        <div class="close" onclick="location.href = '${pageContext.request.contextPath}/AdminServlet?action=logout'">ESC
        </div>
        <!-- User Management -->
        <div class="Reload" onclick="location.href = '${pageContext.request.contextPath}/AdminServlet'">Reload!</div>
        <section class="card">
            <h2>User Management</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="userTable">
                    <c:forEach var="userItem" items="${userList}">
                        <tr>
                            <td>${userItem.id}</td>
                            <td>${userItem.userName}</td>
                            <td>${userItem.role}</td>
                            <td><button
                                    onclick="location.href = '${pageContext.request.contextPath}/AdminServlet?action=updateRole&userUpdateId=${userItem.id}'">Change
                                    Role</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Club Management -->
        <section class="card">
            <h2>Club Management</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Created At</th>
                        <th>Club Chairman</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="clubTable">
                    <c:forEach var="clubItem" items="${clubList}">
                        <tr>
                            <td>${clubItem.id}</td>
                            <td>${clubItem.name}</td>
                            <td>${clubItem.type}</td>
                            <td>${clubItem.date}</td>
                            <td>${clubItem.clubChairman}</td>
                            <td><button class="delete"
                                    onclick="location.href = '${pageContext.request.contextPath}/AdminServlet?action=deleteClub&clubDeleteId=${clubItem.id}'">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </div>

    <script>
        function toggleRole(userId) {
            alert('Role changed for user ID: ' + userId);
        }

        function deleteClub(clubId) {
            alert('Club ID ' + clubId + ' deleted.');
        }
    </script>
</body>

</html>