<div class="container search-box">
    <form action="${pageContext.request.contextPath}/searchFlights" method="get" class="row g-3">
        <div class="col-md-3">
            <label for="origin" class="form-label">From</label>
            <select class="form-select" id="origin" name="origin" required>
                <option value="">-- Select Departure City --</option>
                <c:forEach var="city" items="${cities}">
                    <option value="${city.name}">${city.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-3">
            <label for="destination" class="form-label">To</label>
            <select class="form-select" id="destination" name="destination" required>
                <option value="">-- Select Destination City --</option>
                <c:forEach var="city" items="${cities}">
                    <option value="${city.name}">${city.name}</option>
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