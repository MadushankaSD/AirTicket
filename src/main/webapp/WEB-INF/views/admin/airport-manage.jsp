<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Airports</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="container mt-5">
    <h2 class="text-center mb-4">Airport Management</h2>

    <form action="${pageContext.request.contextPath}/manage/airports/create" method="post" class="mb-4">
        <div class="row g-3">
            <div class="col-md-2">
                <input type="text" name="code" class="form-control" placeholder="Code (e.g., LAX)" required />
            </div>
            <div class="col-md-3">
                <input type="text" name="name" class="form-control" placeholder="Airport Name" required />
            </div>
            <div class="col-md-3">
                <input type="text" name="city" class="form-control" placeholder="City" />
            </div>
            <div class="col-md-3">
                <input type="text" name="country" class="form-control" placeholder="Country" />
            </div>
            <div class="col-md-1">
                <button type="submit" class="btn btn-success w-100">Add</button>
            </div>
        </div>
    </form>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>City</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="airport" items="${airports}">
            <tr>
                <td>${airport.id}</td>
                <td>${airport.code}</td>
                <td>${airport.name}</td>
                <td>${airport.city}</td>
                <td>${airport.country}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/airports/delete/${airport.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
