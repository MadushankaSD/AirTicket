<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="com.sl.nextflight.model.UserDto" %>
<%
    UserDto user = (UserDto) session.getAttribute("user");
    if (user == null || !"ADMIN".equals(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Administrator Dashboard | Next Flight</title>

    <style>
        body            { margin:0; padding:0; font-family:'Segoe UI',sans-serif; background:#f4f9fb;}
        header          { background:#16222e; color:#fff; padding:20px; text-align:center;}
        header h1       { margin:0; font-size:28px;}
        header span      { color:#ed856d;}
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
    <h1>NEXT <span>FLIGHT</span> – Administrator Dashboard</h1>
    <div style="margin-top:8px;">Welcome,&nbsp;<%= user.getUsername() %> |
        <a href="<%= request.getContextPath() %>/home" style="color:white; text-decoration:none;">Home</a>
        |
        <a href="<%= request.getContextPath() %>/logout" style="color:#ed856d; text-decoration:none;">Logout</a>
    </div>
</header>

<div class="wrap">

    <h2>Customer Features</h2>
    <div class="grid">
        <div class="card">
            <h3>Search Flights</h3>
            <p>Look up direct &amp; transit routes with seat‑class availability.</p>
            <a href="<%= request.getContextPath() %>/flights/search">Search</a>
        </div>
        <div class="card">
            <h3>My Bookings</h3>
            <p>View, cancel or print your own reservations.</p>
            <a href="<%= request.getContextPath() %>/my-bookings">View</a>
        </div>
    </div>

    <h2>Operator Tools</h2>
    <div class="grid">
        <div class="card">
            <h3>Create Booking</h3>
            <p>Book flights on behalf of customers.</p>
            <a href="<%= request.getContextPath() %>/operator/bookings/new">Create</a>
        </div>
        <div class="card">
            <h3>Operational Reports</h3>
            <p>Revenue &amp; performance summaries.</p>
            <a href="<%= request.getContextPath() %>/admin/reports/operations">Generate</a>
        </div>
    </div>

    <h2>Administrator Controls</h2>
    <div class="grid">
        <div class="card">
            <h3>User Accounts</h3>
            <p>Create, update or deactivate users.</p>
            <a href="<%= request.getContextPath() %>/admin/users-management">Manage</a>
        </div>

        <div class="card">
            <h3>Airplanes &amp; Airports</h3>
            <p>Maintain aircraft and airport master data.</p>
            <a href="<%= request.getContextPath() %>/manage/aircraft">Maintain-Aircraft</a>
            <a href="<%= request.getContextPath() %>/manage/airport">Maintain-Airport</a>
        </div>
        <div class="card">
            <h3>Flight Scheduling</h3>
            <p>Plan flights – auto‑checks plane location &amp; overlap.</p>
            <a href="<%= request.getContextPath() %>/manage/flights">Schedule</a>
        </div>

        <div class="card">
            <h3>Find Bookings</h3>
            <p>Search by booking ID or customer details.</p>
            <a href="<%= request.getContextPath() %>/manage/bookings/search">Retrieve</a>
        </div>
        <div class="card">
            <h3>Passenger Manifest</h3>
            <p>Seat list for any scheduled flight.</p>
            <a href="<%= request.getContextPath() %>/operator/reports/manifest">Generate</a>
        </div>

        <div class="card">
            <h3>Traffic Reports</h3>
            <p>Arrivals / departures by airport &amp; date range.</p>
            <a href="<%= request.getContextPath() %>/operator/reports/traffic">View</a>
        </div>
    </div>

</div>

</body>
</html>
