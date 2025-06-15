<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="com.sl.nextflight.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"MANAGER".equals(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard - Air Ticket System</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #eef2f5;
        }
        header {
            background-color: #2a3f54;
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
            background-color: #4db8ff;
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

<jsp:include page="header.jsp" />
<h1>Manager Dashboard</h1>
<div class="container">
    <div class="grid">
        <div class="card">
            <h3>Create Booking</h3>
            <p>Create booking for customer.</p>
            <a href="<%= request.getContextPath() %>/manager/booking">Book</a>
        </div>
        <div class="card">
            <h3>Flight Schedule</h3>
            <p>Monitor all scheduled flights and routes.</p>
            <a href="<%= request.getContextPath() %>/manager/flights">View</a>
        </div>
        <div class="card">
            <h3>Manage Airplane</h3>
            <p>Check and manage assigned flight crews.</p>
            <a href="<%= request.getContextPath() %>/manager/crew">Manage</a>
        </div>
        <div class="card">
            <h3>Booking Status</h3>
            <p>Track real-time seat bookings and availability.</p>
            <a href="<%= request.getContextPath() %>/manager/bookings">Track</a>
        </div>
        <div class="card">
            <h3>Operational Reports</h3>
            <p>Access daily or weekly flight operation reports.</p>
            <a href="<%= request.getContextPath() %>/manager/reports">Access</a>
        </div>
        <div class="card">
            <h3>Staff Communication</h3>
            <p>Send updates or messages to staff and crew.</p>
            <a href="<%= request.getContextPath() %>/manager/messages">Open</a>
        </div>
    </div>
</div>
</body>
</html>
