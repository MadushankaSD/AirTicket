<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sl.nextflight.entity.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Users - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function toggleCreateForm() {
            const form = document.getElementById("createUserForm");
            form.classList.toggle("d-none");
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">User Management</h2>

    <!-- Search Form -->
    <form class="mb-4" method="get" action="${pageContext.request.contextPath}/admin/users/search">
        <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="Search by username or email..." value="${param.keyword}">
            <button class="btn btn-outline-secondary" type="submit">Search</button>
        </div>
    </form>

    <!-- Create Button -->
    <div class="mb-3 text-end">
        <button type="button" class="btn btn-success" onclick="toggleCreateForm()">+ Create User</button>
    </div>

    <!-- Create User Form (initially hidden) -->
    <div id="createUserForm" class="card p-4 mb-4 d-none">
        <h4>Create New User</h4>
        <form action="${pageContext.request.contextPath}/admin/users/save" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username *</label>
                <input type="text" name="username" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email *</label>
                <input type="email" name="email" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password *</label>
                <input type="password" name="password" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="roleId" class="form-label">Role *</label>
                <select name="roleId" class="form-select" required>
                    <option value="">-- Select Role --</option>
                    <option value="ADMIN">ADMIN</option>
                    <option value="CUSTOMER">CUSTOMER</option>
                    <option value="OPERATOR">OPERATOR</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Save</button>
            <button type="button" class="btn btn-secondary ms-2" onclick="toggleCreateForm()">Cancel</button>
        </form>
    </div>

    <!-- Success message -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <!-- User Table Placeholder -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Roles</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <span class="badge bg-primary">${user.roles.name}</span>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.enabled}">Enabled</c:when>
                        <c:otherwise>Disabled</c:otherwise>
                    </c:choose>
                </td>
                <td class="d-flex gap-2">
                    <a href="${pageContext.request.contextPath}/admin/users/edit/${user.id}" class="btn btn-sm btn-warning">Edit</a>
                    <form action="${pageContext.request.contextPath}/admin/users/toggle/${user.id}" method="post">
                        <c:choose>
                            <c:when test="${user.enabled}">
                                <button class="btn btn-danger btn-sm" onclick="disableUser(${user.id})">Disable</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-danger btn-sm" onclick="disableUser(${user.id})">Enable</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function disableUser(userId) {
        if (confirm("Are you sure you want to disable this user?")) {
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/user/desabled',
                type: 'GET',
                data: { userId: userId },
                success: function(response) {
                    if (response === true || response === 'true') {
                        alert("User disabled successfully.");
                        location.reload();
                    } else {
                        alert("Failed to disable user.");
                    }
                }
            });
        }
    }
</script>

</body>
</html>
