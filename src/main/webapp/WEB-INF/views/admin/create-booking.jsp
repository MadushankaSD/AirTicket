<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sl.nextflight.dto.FlightSearchResult" %>

<%
    List<FlightSearchResult> flights = (List<FlightSearchResult>) request.getAttribute("flights");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - Create Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f7fa;
            padding: 30px;
        }
        .form-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 750px;
            margin: auto;
        }
        .form-title {
            text-align: center;
            margin-bottom: 25px;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="form-container">
    <h3 class="form-title">Admin: Create Booking for User</h3>

    <form action="${pageContext.request.contextPath}/admin/bookForUser" method="post">
        <!-- Flight Selection -->
        <div class="mb-3">
            <label for="flightId" class="form-label">Select Flight</label>
            <select class="form-select" name="flightId" required>
                <option value="">-- Select a Flight --</option>
                <% if (flights != null) {
                    for (FlightSearchResult f : flights) { %>
                <option value="<%= f.getFlightId() %>">
                    <%= f.getAirline() %> - <%= f.getFlightNumber() %>
                    [<%= f.getOrigin() %> ➔ <%= f.getDestination() %>,
                    Depart: <%= f.getDepartureTime() %>]
                </option>
                <%  }
                } else { %>
                <option disabled>No flights available</option>
                <% } %>
            </select>
        </div>

        <!-- Travel Class -->
        <div class="mb-3">
            <label for="travelClass" class="form-label">Class</label>
            <select class="form-select" name="travelClass" required>
                <option value="ECONOMY">Economy</option>
                <option value="BUSINESS">Business</option>
                <option value="FIRST">First Class</option>
            </select>
        </div>

        <!-- Seat Count -->
        <div class="mb-3">
            <label for="seatCount" class="form-label">Number of Seats</label>
            <input type="number" class="form-control" name="seatCount" min="1" required>
        </div>

        <hr/>

        <!-- Passenger Info -->
        <div class="mb-3">
            <label for="passengerName" class="form-label">Passenger Name</label>
            <input type="text" class="form-control" name="passengerName" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Passenger Email</label>
            <input type="email" class="form-control" name="email" required>
        </div>

        <div class="mb-3">
            <label for="mobile" class="form-label">Passenger Mobile</label>
            <input type="tel" class="form-control" name="mobile" pattern="[0-9]{10}" required>
        </div>

        <!-- Submit -->
        <div class="text-center">
            <button type="submit" class="btn btn-success">Create Booking</button>
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>
