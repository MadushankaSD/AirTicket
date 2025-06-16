<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Air Ticket Booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>

<jsp:include page="common/header.jsp" />
<!-- Hero Section -->
<section class="hero">
    <div class="container">
        <h1 class="display-4">Book Your Next Flight</h1>
        <p class="lead">Find the best deals on domestic and international flights</p>
    </div>
</section>

<!-- Search Form -->
<div class="container search-box">
    <form action="${pageContext.request.contextPath}/searchFlights" method="get" class="row g-3">
        <!-- Origin Dropdown -->
        <div class="col-md-3">
            <label for="origin" class="form-label">From</label>
            <select class="form-select" id="origin" name="origin" required>
                <option value="">-- Select Departure City --</option>
                <c:forEach var="entry" items="${originCities}">
                    <option value="${entry.value}">${entry.key}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Destination Dropdown -->
        <div class="col-md-3">
            <label for="destination" class="form-label">To</label>
            <select class="form-select" id="destination" name="destination" required>
                <option value="">-- Select Destination City --</option>
                <c:forEach var="entry" items="${destinationCities}">
                    <option value="${entry.value}">${entry.key}</option>
                </c:forEach>
            </select>
        </div>


        <div class="col-md-3">
            <label for="departureDate" class="form-label">Departure Date</label>
            <input type="date" class="form-control" id="departureDate" name="departureDate" required>
        </div>

        <div class="col-md-3">
            <label for="classType" class="form-label">Class</label>
            <select class="form-select" id="classType" name="classType">
                <option value="ECONOMY">Economy</option>
                <option value="BUSINESS">Business</option>
                <option value="FIRST">First</option>
            </select>
        </div>

        <div class="col-12 text-center mt-3">
            <button type="submit" class="btn btn-primary btn-lg">Search Flights</button>
        </div>
    </form>
</div>
<!-- Popular Destinations -->
<jsp:include page="home/popular.jsp" />

<!-- Footer -->
<div class="footer">
    <jsp:include page="common/footer.jsp" />
</div>

</body>
<script>
    const originSelect = document.getElementById('origin');
    const destinationSelect = document.getElementById('destination');

    // Save original destination options to restore later
    const originalDestinationOptions = Array.from(destinationSelect.options);

    originSelect.addEventListener('change', function () {
        const selectedOrigin = this.value;

        // Clear current destination options
        destinationSelect.innerHTML = '';

        if (selectedOrigin) {
            // Enable destination dropdown
            destinationSelect.disabled = false;

            // Add back only the destination options excluding the selected origin
            originalDestinationOptions.forEach(option => {
                if (option.value !== selectedOrigin || option.value === '') {
                    destinationSelect.appendChild(option.cloneNode(true));
                }
            });

            // Reset selected destination
            destinationSelect.value = '';
        } else {
            // If no origin selected, disable and reset destination dropdown
            destinationSelect.disabled = true;
            originalDestinationOptions.forEach(option => {
                destinationSelect.appendChild(option.cloneNode(true));
            });
            destinationSelect.value = '';
        }
    });

    // Initial load: disable destination if no origin selected
    window.addEventListener('DOMContentLoaded', () => {
        if (!originSelect.value) {
            destinationSelect.disabled = true;
        }
    });
</script>

</html>
