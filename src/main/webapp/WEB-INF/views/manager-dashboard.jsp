<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="com.sl.nextflight.model.UserDto" %>
<%
    UserDto user = (UserDto) session.getAttribute("user");
    if (user == null || !"OPERATOR".equals(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Operator Dashboard - Next Flight</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #1f2d3d;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .dashboard-container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
        }
        h2 {
            color: #1f2d3d;
        }
        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 20px;
        }
        .card {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            text-align: center;
        }
        .card h3 {
            margin-top: 0;
        }
        .card a {
            display: inline-block;
            margin-top: 10px;
            padding: 10px 16px;
            background-color: #0077b6;
            color: white;
            border-radius: 6px;
            text-decoration: none;
        }
        .card a:hover {
            background-color: #005f8f;
        }
    </style>
</head>
<body>

<header>
    <h1>NEXT <span>FLIGHT</span> – Operator Dashboard</h1>
    <div style="margin-top:8px;">Welcome,&nbsp;<%= user.getUsername() %> |
        <a href="<%= request.getContextPath() %>/home" style="color:white; text-decoration:none;">Home</a>
        |
        <a href="<%= request.getContextPath() %>/logout" style="color:#ed856d; text-decoration:none;">Logout</a>
    </div>

</header>
<div class="dashboard-container">
    <h2>Customer & Operator Functions</h2>
    <div class="grid">
        <div class="card">
            <h3>Search Flights</h3>
            <p>Find direct and transit flights with available seats.</p>
            <a href="<%= request.getContextPath() %>/flights/search">Search</a>
        </div>
        <div class="card">
            <h3>My Bookings</h3>
            <p>View your personal bookings and details.</p>
            <a href="<%= request.getContextPath() %>/user/bookings">View</a>
        </div>
        <div class="card">
            <h3>Create Booking</h3>
            <p>Book flights on behalf of customers.</p>
            <a href="<%= request.getContextPath() %>/operator/bookings/new">Create</a>
        </div>
    </div>

    <h2 style="margin-top: 40px;">Flight & Operations Management</h2>
    <div class="grid">
        <div class="card">
            <h3>Flight Scheduling</h3>
            <p>Schedule flights and validate against conflicts.</p>
            <a href="<%= request.getContextPath() %>/operator/flights/schedule">Schedule</a>
        </div>
        <div class="card">
            <h3>Manage Airplanes</h3>
            <p>Maintain airplane and airport records.</p>
            <a href="<%= request.getContextPath() %>/operator/airplanes">Manage</a>
        </div>
        <div class="card">
            <h3>Passenger Manifest</h3>
            <p>Generate seat list for any scheduled flight.</p>
            <a href="<%= request.getContextPath() %>/operator/reports/manifest">Generate</a>
        </div>
        <div class="card">
            <h3>Retrieve Bookings</h3>
            <p>Find bookings by ID or customer details.</p>
            <a href="<%= request.getContextPath() %>/operator/bookings/search">Retrieve</a>
        </div>
        <div class="card">
            <h3>Flight Reports</h3>
            <p>List arriving and departing flights by airport & timeframe.</p>
            <a href="<%= request.getContextPath() %>/operator/reports/traffic">View</a>
        </div>
    </div>
</div>

</body>
</html>
