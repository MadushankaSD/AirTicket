<%@ page import="com.sl.nextflight.model.User" %>
<%@ page session="true" %>
<header style="background-color:#62bfd5; padding:15px 0; color:white; font-family: 'Segoe UI', sans-serif;">
    <div style="max-width:1200px; margin:0 auto; display:flex; justify-content:space-between; align-items:center;">
        <div style="font-size:28px; font-weight:bold;">
            NEXT <span style="color:#ed856d;">FLIGHT</span>
        </div>
        <nav>
            <a href="${pageContext.request.contextPath}/home" style="color: white; margin-right: 20px; text-decoration: none;">Home</a>
            <a href="${pageContext.request.contextPath}/contact" style="color: white; margin-right: 20px; text-decoration: none;">Contact</a>
            <%
                User user = (User) session.getAttribute("user");
                if (user != null) {
            %>
            <% if ("ADMIN".equals(user.getRole())) { %>
            <a href="${pageContext.request.contextPath}/admin-dashboard">Admin Dashboard</a>
            <% } %>

            <% if ("MANAGER".equals(user.getRole())) { %>
            <a href="${pageContext.request.contextPath}/manager-dashboard">Manager Dashboard</a>
            <% } %>

            <span style="margin-right: 40px; margin-left: 20px">Welcome, <%= user.getName() %></span>
            <a href="#" onclick="logout(event)" style="color: white; text-decoration: none;">Logout</a>

            <%
            } else {
            %>
            <a href="#" onclick="openLoginModal(event)" style="color: white; text-decoration: none;">Login</a>
            <%
                }
            %>

        </nav>
    </div>
</header>
<jsp:include page="login.jsp" />