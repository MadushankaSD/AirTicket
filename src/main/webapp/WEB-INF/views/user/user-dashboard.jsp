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
    <title>User Dashboard | Next Flight</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body            { margin:0; padding:0; font-family:'Segoe UI',sans-serif; background:#f4f9fb;}
        header          { background:#16222e; color:#fff; padding:20px; text-align:center;}
        header h1       { margin:0; font-size:28px;}
        header span     { color:#ed856d;}
        .wrap           { max-width:1200px; margin:40px auto; padding:0 20px;}
        h2              { color:#16222e; margin:40px 0 20px;}
        .grid           { display:grid; gap:20px; grid-template-columns:repeat(auto-fill,minmax(250px,1fr));}
        .card           { background:#fff; border-radius:10px; box-shadow:0 2px 8px rgba(0,0,0,.08); text-align:center; padding:22px;}
        .card h3        { margin:0 0 10px; font-size:18px;}
        .card p         { font-size:14px; color:#555;}
        .card a         { display:inline-block; margin-top:12px; padding:9px 16px; background:#0077b6; color:#fff;
            border-radius:6px; text-decoration:none;}
        .card a:hover   { background:#005f8f;}
    </style>
</head>

<body>

<header>
    <h1>NEXT <span>FLIGHT</span> – User Dashboard</h1>
    <div style="margin-top:8px;">Welcome,&nbsp;<%= user.getUsername() %> |
        <a href="<%= request.getContextPath() %>/home" style="color:white; text-decoration:none;">Home</a>
        |
        <a href="<%= request.getContextPath() %>/logout" style="color:#ed856d; text-decoration:none;">Logout</a>
    </div>
</header>

<div class="wrap">

    <h2>User Features</h2>
    <div class="grid">
        <div class="card">
            <h3>My Bookings</h3>
            <p>View and manage your existing reservations.</p>
            <a href="<%= request.getContextPath() %>/my-bookings">View</a>
        </div>

        <div class="card">
            <h3>Manage Profile</h3>
            <p>Update your personal details and preferences.</p>
            <a href="<%= request.getContextPath() %>/profile">Update</a>
        </div>
    </div>

</div>

</body>
</html>
