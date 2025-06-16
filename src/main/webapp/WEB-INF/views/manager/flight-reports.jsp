<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Flight Reports</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f7fa;
            padding: 30px;
        }
        .report-container {
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            max-width: 1000px;
            margin: auto;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .table th {
            background-color: #007acc;
            color: #fff;
        }
        .section-title {
            margin-top: 30px;
            font-weight: 600;
            color: #333;
        }
    </style>
</head>
<body>
<div class="report-container">
    <h3 class="mb-3">Flight Reports</h3>
    <p class="text-muted">List arriving and departing flights by airport & timeframe.</p>

    <div class="mb-4">
        <label class="form-label">Select Airport</label>
        <select class="form-select w-50">
            <option>JFK - New York</option>
            <option>LAX - Los Angeles</option>
            <option>ORD - Chicago</option>
            <option>DXB - Dubai</option>
        </select>
    </div>

    <div class="mb-4">
        <label class="form-label">Timeframe</label>
        <select class="form-select w-50">
            <option>Today</option>
            <option>Last 24 Hours</option>
            <option>This Week</option>
        </select>
    </div>

    <hr>

    <h5 class="section-title">Departing Flights</h5>
    <table class="table table-bordered table-hover mt-2">
        <thead>
        <tr>
            <th>Flight</th>
            <th>Destination</th>
            <th>Departure Time</th>
            <th>Gate</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>AA123</td>
            <td>Los Angeles</td>
            <td>09:30 AM</td>
            <td>A12</td>
            <td>On Time</td>
        </tr>
        <tr>
            <td>DL456</td>
            <td>Chicago</td>
            <td>11:15 AM</td>
            <td>B5</td>
            <td>Delayed</td>
        </tr>
        <tr>
            <td>EK789</td>
            <td>Dubai</td>
            <td>01:00 PM</td>
            <td>C3</td>
            <td>On Time</td>
        </tr>
        </tbody>
    </table>

    <h5 class="section-title">Arriving Flights</h5>
    <table class="table table-bordered table-hover mt-2">
        <thead>
        <tr>
            <th>Flight</th>
            <th>Origin</th>
            <th>Arrival Time</th>
            <th>Gate</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>BA101</td>
            <td>London</td>
            <td>08:45 AM</td>
            <td>D1</td>
            <td>Landed</td>
        </tr>
        <tr>
            <td>AF202</td>
            <td>Paris</td>
            <td>10:20 AM</td>
            <td>E2</td>
            <td>On Time</td>
        </tr>
        <tr>
            <td>LH303</td>
            <td>Frankfurt</td>
            <td>12:10 PM</td>
            <td>F4</td>
            <td>Delayed</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
