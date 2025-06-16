<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="com.sl.nextflight.model.UserDto" %>
<%
    UserDto user = (UserDto) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard - Next Flight</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f9fb;
        }
        header {
            background-color: #62bfd5;
            padding: 15px;
            color: white;
            font-size: 24px;
            font-weight: bold;
        }
        .dashboard-container {
            max-width: 1000px;
            margin: 40px auto;
            background-color: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-top: 0;
            color: #333;
        }
        .actions {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }
        .actions a {
            display: inline-block;
            padding: 12px 20px;
            background-color: #62bfd5;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            width: fit-content;
        }
        .actions a:hover {
            background-color: #50a7bc;
        }
    </style>
</head>
<body>

<jsp:include page="../common/header.jsp" />

<div class="dashboard-container">
    <h1>User Dashboard</h1>
    <h2>Welcome, <%= user.getUsername() %>!</h2>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/searchFlights">🔍 Search Flights</a>
        <a href="${pageContext.request.contextPath}/bookings">📋 My Bookings</a>
        <a href="${pageContext.request.contextPath}/profile">👤 Manage Profile</a>
    </div>
</div>
</body>
</html>
