<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sl.nextflight.entity.Booking" %>

<jsp:include page="../common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <title>Retrieve Bookings</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', sans-serif;
            padding: 20px;
        }

        h2 {
            margin-bottom: 20px;
        }

        .search-box {
            background: #ffffff;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }

        .table th {
            background-color: #007bff;
            color: white;
        }

        .no-results {
            text-align: center;
            font-weight: bold;
            color: #dc3545;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Retrieve Bookings</h2>

    <div class="search-box mb-4">
        <form action="${pageContext.request.contextPath}/searchBookings" method="get" class="row g-3">
            <div class="col-md-4">
                <label for="bookingId" class="form-label">Booking ID</label>
                <input type="number" class="form-control" id="bookingId" name="bookingId" placeholder="Enter Booking ID">
            </div>
            <div class="col-md-4">
                <label for="customerName" class="form-label">Customer Name</label>
                <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Enter Name">
            </div>
            <div class="col-md-4">
                <label for="email" class="form-label">Customer Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email">
            </div>
            <div class="col-12 text-end">
                <button type="submit" class="btn btn-primary">Search</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
            </div>
        </form>
    </div>

    <%
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
        if (bookings != null && !bookings.isEmpty()) {
    %>
    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Booking ID</th>
                <th>Customer</th>
                <th>Email</th>
                <th>Flight</th>
                <th>Class</th>
                <th>Seats</th>
                <th>Total Price</th>
                <th>Booking Time</th>
            </tr>
            </thead>
            <tbody>
            <% for (Booking booking : bookings) { %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getPassengerName() %></td>
                <td><%= booking.getEmail() %></td>
                <td><%= booking.getFlight().getId() %></td>
                <td><%= booking.getTravelClass() %></td>
                <td><%= booking.getSeatCount() %></td>
                <td><%= booking.getBookingTime() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <% } else if (request.getParameter("bookingId") != null || request.getParameter("customerName") != null || request.getParameter("email") != null) { %>
    <div class="no-results">No bookings found.</div>
    <% } %>
</div>

</body>
</html>
