<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Users - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">User Management</h2>

    <form class="mb-4" method="get" action="${pageContext.request.contextPath}/admin/users/search">
        <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="Search by username or email..." value="${param.keyword}">
            <button class="btn btn-outline-secondary" type="submit">Search</button>
        </div>
    </form>

    <div class="mb-3 text-end">
        <a href="${pageContext.request.contextPath}/admin/users/new" class="btn btn-success">+ Create User</a>
    </div>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

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
                <td>${user.username}</td>z
                <td>${user.email}</td>
                <td>
                    <c:forEach var="role" items="${user.roles}">
                        <span class="badge bg-primary">${role.name}</span>
                    </c:forEach>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.enabled}">Enabled</c:when>
                        <c:otherwise>Disabled</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/users/edit/${user.id}" class="btn btn-sm btn-warning">Edit</a>
                    <form action="${pageContext.request.contextPath}/admin/users/toggle/${user.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-sm ${user.enabled ? 'btn-danger' : 'btn-success'}">
                                ${user.enabled ? 'Disable' : 'Enable'}
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
