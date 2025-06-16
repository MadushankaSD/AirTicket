<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sl.nextflight.dto.FlightSearchResult" %>

<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <title>Search Flights</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f7fa;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        form {
            margin-bottom: 30px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
        }
        input, select, button {
            padding: 10px;
            margin-right: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }
        th, td {
            padding: 12px 16px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007acc;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .transit {
            background-color: #fff8dc;
        }
    </style>
</head>
<body>
<div class="wrap">
<h1>Search Available Flights</h1>

<jsp:include page="search-flight-form.jsp" />

<%
    List<FlightSearchResult> results = (List<FlightSearchResult>) request.getAttribute("flights");
    String travelClass = (String) request.getAttribute("travelClass");

    if (results != null && !results.isEmpty()) {
%>
<table>
    <tr>
        <th>Flight</th>
        <th>From</th>
        <th>To</th>
        <th>Departure</th>
        <th>Arrival</th>
        <th>Available Seats</th>
        <th>Price</th>
        <th>Type</th>
        <th>Action</th>
    </tr>
    <% for (FlightSearchResult f : results) {
        String rowClass = f.isTransit() ? "transit" : "";
        int available = 0;
        double price = 0.0;

        switch (travelClass) {
            case "ECONOMY":
                available = f.getAvailableEconomySeats();
                price = f.getEconomyPrice();
                break;
            case "BUSINESS":
                available = f.getAvailableBusinessSeats();
                price = f.getBusinessPrice();
                break;
            case "FIRST":
                available = f.getAvailableFirstClassSeats();
                price = f.getFirstClassPrice();
                break;
        }
    %>
    <tr class="<%= rowClass %>">
        <td><%= f.getAirline() %> - <%= f.getFlightNumber() %></td>
        <td><%= f.getOrigin() %></td>
        <td><%= f.getDestination() %></td>
        <td><%= f.getDepartureTime() %></td>
        <td><%= f.getArrivalTime() %></td>
        <td><%= available %></td>
        <td>$<%= String.format("%.2f", price) %></td>
        <td><%= f.isTransit() ? "Transit" : "Direct" %></td>
        <td>
            <% if (available > 0) { %>
            <form action="${pageContext.request.contextPath}/bookFlight" method="post">
                <input type="hidden" name="flightId" value="<%= f.getFlightId() %>">
                <input type="hidden" name="travelClass" value="<%= travelClass %>">
                <button type="submit">Book</button>
            </form>
            <% } else { %>
            <span style="color: red;">Full</span>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<%
} else if (request.getParameter("origin") != null) {
%>
<p style="color:red;">No flights found for given criteria.</p>
<%
    }
%>
</div>
</body>
</html>
