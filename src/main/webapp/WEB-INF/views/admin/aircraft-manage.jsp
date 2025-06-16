<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Airplane Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Airplane Management</h2>

    <!-- Form to create a new airplane -->
    <form action="${pageContext.request.contextPath}/manage/airplanes/create" method="post" class="mb-4">
        <div class="row g-3">
            <div class="col-md-3">
                <input type="text" name="model" class="form-control" placeholder="Model" required />
            </div>
            <div class="col-md-3">
                <input type="text" name="manufacturer" class="form-control" placeholder="Manufacturer" required />
            </div>
            <div class="col-md-3">
                <input type="number" name="seatCapacity" class="form-control" placeholder="Seat Capacity" required />
            </div>
            <div class="col-md-3 d-grid">
                <button type="submit" class="btn btn-success">Add Airplane</button>
            </div>
        </div>
    </form>

    <!-- Table to list all airplanes -->
    <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Model</th>
            <th>Manufacturer</th>
            <th>Seat Capacity</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="airplane" items="${airplanes}">
            <tr>
                <td>${airplane.id}</td>
                <td>${airplane.model}</td>
                <td>${airplane.manufacturer}</td>
                <td>${airplane.seatCapacity}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/manage/airplanes/delete/${airplane.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this airplane?');">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
