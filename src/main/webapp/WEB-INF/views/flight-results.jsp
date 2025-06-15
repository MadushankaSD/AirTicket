<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Flight Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: #f9f9f9;
        }
        h1 {
            color: #2c3e50;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            background: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px 15px;
            text-align: center;
        }
        th {
            background-color: #2980b9;
            color: white;
        }
        tr:nth-child(even){
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .btn-select {
            background-color: #27ae60;
            color: white;
            border: none;
            padding: 6px 12px;
            cursor: pointer;
            border-radius: 4px;
        }
        .btn-select:hover {
            background-color: #219150;
        }
        .no-results {
            font-style: italic;
            color: #888;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h1>Available Flights</h1>

<c:choose>
    <c:when test="${not empty flightList}">
        <table>
            <thead>
            <tr>
                <th>Airline</th>
                <th>Flight Number</th>
                <th>Departure</th>
                <th>Arrival</th>
                <th>Duration</th>
                <th>Price (USD)</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="flight" items="${flightList}">
                <tr>
                    <td><c:out value="${flight.airline}" /></td>
                    <td><c:out value="${flight.flightNumber}" /></td>
                    <td>
                        <fmt:formatDate value="${flight.departureTime}" pattern="MMM dd, yyyy HH:mm" />
                        <br />
                        <small><c:out value="${flight.departureAirport}" /></small>
                    </td>
                    <td>
                        <fmt:formatDate value="${flight.arrivalTime}" pattern="MMM dd, yyyy HH:mm" />
                        <br />
                        <small><c:out value="${flight.arrivalAirport}" /></small>
                    </td>
                    <td><c:out value="${flight.duration}" /></td>
                    <td>$<fmt:formatNumber value="${flight.price}" type="currency" currencySymbol="" minFractionDigits="2" /></td>
                    <td>
                        <form action="bookFlight" method="post">
                            <input type="hidden" name="flightId" value="${flight.id}" />
                            <button type="submit" class="btn-select">Select</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p class="no-results">No flights available for your search criteria. Please try different dates or airports.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
