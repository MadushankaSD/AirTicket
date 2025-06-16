<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sl.nextflight.entity.User" %>

<jsp:include page="../common/header.jsp" />

<%
    User user = (User) session.getAttribute("user_row"); // replace with your session attribute name
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f7fa;
            font-family: 'Segoe UI', sans-serif;
            padding: 40px;
        }
        .profile-container {
            background-color: #fff;
            padding: 30px;
            max-width: 600px;
            margin: auto;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<div class="profile-container">
    <h3 class="mb-4">Manage Profile</h3>
    <p class="text-muted">Update your personal details and preferences.</p>

    <% if (user != null) { %>
    <form id="profile">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" class="form-control" name="name" value="<%= user.getUsername() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" name="email" value="<%= user.getEmail() %>" required readonly>
        </div>

        <button type="submit" class="btn btn-success">Update Profile</button>
    </form>
    <% } else { %>
    <div class="alert alert-danger mt-4">User not found. Please log in</a>.</div>
    <% } %>
</div>

</body>
</html>
