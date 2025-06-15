<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="com.sl.nextflight.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"ADMIN".equals(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Air Ticket System</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f7fc;
        }
        header {
            background-color: #1c2b36;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .container {
            max-width: 1100px;
            margin: 40px auto;
            padding: 20px;
        }
        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        .card {
            background: white;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            transition: 0.3s;
            text-align: center;
        }
        .card:hover {
            box-shadow: 0 4px 14px rgba(0,0,0,0.2);
        }
        .card h3 {
            margin-bottom: 10px;
        }
        .card a {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 14px;
            background-color: #62bfd5;
            color: white;
            border-radius: 6px;
            text-decoration: none;
        }
        .logout {
            float: right;
            margin-top: -45px;
            margin-right: 20px;
        }
    </style>
</head>
<body>
<header>
    <h1>Admin Dashboard</h1>
    <div class="logout">
        <span>Welcome, <%= user.getName() %></span>
        |
        <a href="<%= request.getContextPath() %>/logout" style="color: #ed856d;">Logout</a>
    </div>
</header>

<div class="container">
    <div class="grid">
        <div class="card">
            <h3>Manage Flights</h3>
            <p>Add, update, or remove flights.</p>
            <a href="<%= request.getContextPath() %>/admin/flights">Go</a>
        </div>
        <div class="card">
            <h3>Manage Bookings</h3>
            <p>View or cancel passenger bookings.</p>
            <a href="<%= request.getContextPath() %>/admin/bookings">Go</a>
        </div>
        <div class="card">
            <h3>Manage Users</h3>
            <p>View or deactivate registered users.</p>
            <a href="<%= request.getContextPath() %>/admin/users">Go</a>
        </div>
        <div class="card">
            <h3>Reports</h3>
            <p>Check revenue and flight performance reports.</p>
            <a href="<%= request.getContextPath() %>/admin/reports">Go</a>
        </div>
        <div class="card">
            <h3>Support Tickets</h3>
            <p>View and respond to user support tickets.</p>
            <a href="<%= request.getContextPath() %>/admin/support">Go</a>
        </div>
    </div>
</div>
</body>
</html>