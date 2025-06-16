<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Manage My Bookings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="container mt-5">
    <h2 class="mb-4">My Bookings</h2>

    <c:if test="${empty bookings}">
        <div class="alert alert-info">No bookings found.</div>
    </c:if>

    <c:if test="${not empty bookings}">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
            <tr>
                <th>Booking ID</th>
                <th>Flight</th>
                <th>From</th>
                <th>To</th>
                <th>Date</th>
                <th>Departure</th>
                <th>Class</th>
                <th>Seats</th>
                <th>Passenger</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Booked At</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr>
                    <td>${booking.id}</td>
                    <td>${booking.flight.airplane.model}</td>
                    <td>${booking.flight.origin.city}</td>
                    <td>${booking.flight.destination.city}</td>
                    <td>${booking.flight.date}</td>
                    <td>${booking.flight.departureTime}</td>
                    <td>${booking.travelClass}</td>
                    <td>${booking.seatCount}</td>
                    <td>${booking.passengerName}</td>
                    <td>${booking.email}</td>
                    <td>${booking.mobile}</td>
                    <td>${booking.bookingTime}</td>
                    <td>
                        <button class="btn btn-danger btn-sm"
                                onclick="cancelBooking(${booking.id})">
                            Cancel
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<script>
    function cancelBooking(bookingId) {
        if (!confirm("Are you sure you want to cancel this booking?")) {
            return;
        }

        fetch(`${pageContext.request.contextPath}/cancelBooking`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Requested-With': 'XMLHttpRequest'
            },
            body: new URLSearchParams({ bookingId })
        })
            .then(response => {
                if (response.ok) {
                    alert("Booking cancelled successfully.");

                    // If you're using a modal, close it
                    const modal = bootstrap.Modal.getInstance(document.getElementById('yourModalId'));
                    if (modal) modal.hide();

                    // Reload to reflect changes
                    location.reload();
                } else {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
            })
            .catch(error => {
                alert("Error cancelling booking: " + error.message);
            });
    }
</script>
</body>
</html>
