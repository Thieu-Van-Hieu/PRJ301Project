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
    <style>/* styles.css */
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
        }
        
        h1 {
            text-align: center;
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
        
        table, th, td {
            border: 1px solid #ddd;
        }
        
        th, td {
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
    <div class="container">
        <h1>Admin Dashboard</h1>
        
        <!-- User Management -->
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
                    <tr>
                        <td>1</td>
                        <td>johnDoe123</td>
                        <td>user</td>
                        <td><button onclick="toggleRole(1)">Change Role</button></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>adminUser</td>
                        <td>admin</td>
                        <td><button onclick="toggleRole(2)">Change Role</button></td>
                    </tr>
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
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="clubTable">
                    <tr>
                        <td>1</td>
                        <td>Tech Club</td>
                        <td>Technology</td>
                        <td>2024-01-10</td>
                        <td><button class="delete" onclick="deleteClub(1)">Delete</button></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Art Club</td>
                        <td>Arts</td>
                        <td>2023-05-20</td>
                        <td><button class="delete" onclick="deleteClub(2)">Delete</button></td>
                    </tr>
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

