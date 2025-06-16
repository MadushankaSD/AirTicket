<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<jsp:include page="../common/header.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <title>Passenger Manifest</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <style>
        body {
            background-color: #f4f6f8;
            padding: 20px;
        }
        .table thead {
            background-color: #007bff;
            color: white;
        }
        h2 {
            margin-bottom: 20px;
            font-weight: 600;
        }
        .flight-header {
            background-color: #e9ecef;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">

    <h2>Passenger Manifest</h2>

    <!-- Static Flight Information -->
    <div class="flight-header">
        <h5>Flight: UL101</h5>
        <p><strong>Route:</strong> Colombo (CMB) → Dubai (DXB)</p>
        <p><strong>Date:</strong> 2025-06-20</p>
        <p><strong>Departure Time:</strong> 10:00 AM</p>
    </div>

    <!-- Passenger List -->
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>Passenger Name</th>
                <th>Class</th>
                <th>Seats Booked</th>
                <th>Email</th>
                <th>Mobile</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>John Doe</td>
                <td>Economy</td>
                <td>2</td>
                <td>john@example.com</td>
                <td>0771234567</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Jane Smith</td>
                <td>Business</td>
                <td>1</td>
                <td>jane.smith@gmail.com</td>
                <td>0779876543</td>
            </tr>
            <tr>
                <td>3</td>
                <td>Ahmed Khan</td>
                <td>First</td>
                <td>1</td>
                <td>ahmed.k@example.com</td>
                <td>0761112233</td>
            </tr>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
