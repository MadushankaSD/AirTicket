<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Flight Schedule Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="container mt-5">
    <h2 class="text-center mb-4">Flight Schedule Management</h2>

    <!-- Search Flights -->
    <form class="row g-3 mb-4" action="${pageContext.request.contextPath}/manage/flights/search" method="get">
        <div class="col-md-3">
            <select name="originId" class="form-select" required>
                <option value="">Select Origin</option>
                <c:forEach var="airport" items="${airports}">
                    <option value="${airport.id}" ${param.originId == airport.id ? 'selected' : ''}>
                            ${airport.code} - ${airport.city}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-3">
            <select name="destinationId" class="form-select" required>
                <option value="">Select Destination</option>
                <c:forEach var="airport" items="${airports}">
                    <option value="${airport.id}" ${param.destinationId == airport.id ? 'selected' : ''}>
                            ${airport.code} - ${airport.city}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-3">
            <input type="date" name="date" class="form-control" value="${param.date}" required/>
        </div>
        <div class="col-md-3 d-grid">
            <button type="submit" class="btn btn-primary">Search Flights</button>
        </div>
    </form>

    <!-- Add/Edit Flight Form -->
    <h4>${flightToEdit != null ? 'Edit Flight' : 'Add New Flight'}</h4>
    <form action="${pageContext.request.contextPath}/manage/flights/${flightToEdit != null ? 'update/' + flightToEdit.id : 'create'}" method="post" class="mb-5">
        <div class="row g-3">
            <div class="col-md-3">
                <select name="airplaneId" class="form-select" required>
                    <option value="">Select Airplane</option>
                    <c:forEach var="airplane" items="${airplanes}">
                        <option value="${airplane.id}" ${flightToEdit != null && flightToEdit.airplane.id == airplane.id ? 'selected' : ''}>
                                ${airplane.model} (${airplane.manufacturer})
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <select name="originId" class="form-select" required>
                    <option value="">Select Origin</option>
                    <c:forEach var="airport" items="${airports}">
                        <option value="${airport.id}" ${flightToEdit != null && flightToEdit.origin.id == airport.id ? 'selected' : ''}>
                                ${airport.code} - ${airport.city}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <select name="destinationId" class="form-select" required>
                    <option value="">Select Destination</option>
                    <c:forEach var="airport" items="${airports}">
                        <option value="${airport.id}" ${flightToEdit != null && flightToEdit.destination.id == airport.id ? 'selected' : ''}>
                                ${airport.code} - ${airport.city}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <input type="date" name="date" class="form-control" required
                       value="${flightToEdit != null ? flightToEdit.date.toLocalDate() : ''}"/>
            </div>

            <div class="col-md-3">
                <input type="time" name="departureTime" class="form-control" required
                       value="${flightToEdit != null ? flightToEdit.departureTime.toLocalTime() : ''}"/>
            </div>
            <div class="col-md-3">
                <input type="time" name="arrivalTime" class="form-control" required
                       value="${flightToEdit != null ? flightToEdit.arrivalTime.toLocalTime() : ''}"/>
            </div>

            <div class="col-md-2">
                <input type="checkbox" name="transit" ${flightToEdit != null && flightToEdit.transit ? 'checked' : ''}/>
                Transit
            </div>

            <div class="col-md-1">
                <input type="number" name="economySeats" class="form-control" min="0" placeholder="E Seats" required
                       value="${flightToEdit != null ? flightToEdit.economySeats : ''}"/>
            </div>
            <div class="col-md-1">
                <input type="number" name="businessSeats" class="form-control" min="0" placeholder="B Seats" required
                       value="${flightToEdit != null ? flightToEdit.businessSeats : ''}"/>
            </div>
            <div class="col-md-1">
                <input type="number" name="firstClassSeats" class="form-control" min="0" placeholder="F Seats" required
                       value="${flightToEdit != null ? flightToEdit.firstClassSeats : ''}"/>
            </div>

            <div class="col-md-12 d-grid">
                <button type="submit" class="btn btn-success">${flightToEdit != null ? 'Update Flight' : 'Add Flight'}</button>
                <c:if test="${flightToEdit != null}">
                    <a href="${pageContext.request.contextPath}/manage/flights" class="btn btn-secondary mt-2">Cancel Edit</a>
                </c:if>
            </div>
        </div>
    </form>

    <!-- Flight List -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Airplane</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Date</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Transit</th>
            <th>Seats (E/B/F)</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="flight" items="${flights}">
            <tr>
                <td>${flight.id}</td>
                <td>${flight.airplane.model}</td>
                <td>${flight.origin.code} - ${flight.origin.city}</td>
                <td>${flight.destination.code} - ${flight.destination.city}</td>
                <td>${flight.date.toString().substring(0, 10)}</td>
                <td>${flight.departureTime.toString().substring(11, 16)}</td>
                <td>${flight.arrivalTime.toString().substring(11, 16)}</td>
                <td><c:choose>
                    <c:when test="${flight.transit}">Yes</c:when>
                    <c:otherwise>No</c:otherwise>
                </c:choose></td>
                <td>${flight.economySeats} / ${flight.businessSeats} / ${flight.firstClassSeats}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/manage/flights/edit/${flight.id}" class="btn btn-primary btn-sm">Edit</a>
                    <form action="${pageContext.request.contextPath}/manage/flights/delete/${flight.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-danger btn-sm"
                                onclick="return confirm('Are you sure you want to delete this flight?');">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
