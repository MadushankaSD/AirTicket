<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <title>Operational Reports</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <style>
        body {
            background-color: #f8f9fa;
            padding: 20px;
        }

        h2 {
            margin-bottom: 20px;
        }

        .card {
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            margin-bottom: 20px;
        }

        .card-title {
            font-weight: bold;
            color: #007bff;
        }

        .summary-table th {
            background-color: #343a40;
            color: white;
        }

        .highlight {
            color: #28a745;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Operational Reports</h2>

    <!-- Summary Cards -->
    <div class="row">
        <div class="col-md-4">
            <div class="card p-3">
                <h5 class="card-title">Total Revenue</h5>
                <p class="highlight">$154,230.75</p>
                <small>From all completed bookings</small>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3">
                <h5 class="card-title">Flights Operated</h5>
                <p class="highlight">327</p>
                <small>Including transit & direct</small>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3">
                <h5 class="card-title">Total Passengers</h5>
                <p class="highlight">1,238</p>
                <small>Seats booked across all flights</small>
            </div>
        </div>
    </div>

    <!-- Table -->
    <div class="table-responsive mt-4">
        <table class="table table-bordered summary-table">
            <thead>
            <tr>
                <th>Date</th>
                <th>Flight</th>
                <th>Route</th>
                <th>Class</th>
                <th>Passengers</th>
                <th>Revenue</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>2025-06-10</td>
                <td>UL101</td>
                <td>Colombo → Dubai</td>
                <td>Economy</td>
                <td>98</td>
                <td>$14,700</td>
            </tr>
            <tr>
                <td>2025-06-11</td>
                <td>EK202</td>
                <td>Colombo → London</td>
                <td>Business</td>
                <td>28</td>
                <td>$22,400</td>
            </tr>
            <tr>
                <td>2025-06-12</td>
                <td>UL105</td>
                <td>Colombo → Singapore</td>
                <td>First</td>
                <td>6</td>
                <td>$7,800</td>
            </tr>
            <tr>
                <td>2025-06-12</td>
                <td>UL110</td>
                <td>Colombo → Mumbai</td>
                <td>Economy</td>
                <td>102</td>
                <td>$10,200</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
