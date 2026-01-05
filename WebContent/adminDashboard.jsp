<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.onlinebank.model.User" %>
<%@ page session="true" %>

<%
    // Admin session check
    Boolean isAdmin = (Boolean) session.getAttribute("admin");
    if (isAdmin == null || !isAdmin) {
        response.sendRedirect("adminLogin.jsp");
        return;
    }

    List<User> users = (List<User>) request.getAttribute("users");
%>

<html>
<head>
    <title>Admin Dashboard</title>
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">Admin Dashboard</font>
</h2>

<h3 align="center">
    <font color="#5a189a">Registered Users</font>
</h3>

<table align="center" border="1" cellpadding="12" bgcolor="#f3e8ff" width="60%">
    <tr bgcolor="#e0cfff">
        <th>User ID</th>
        <th>Full Name</th>
        <th>Username</th>
    </tr>

    <%
        if (users != null && !users.isEmpty()) {
            for (User u : users) {
    %>
    <tr align="center">
        <td><%= u.getId() %></td>
        <td><%= u.getFullName() != null ? u.getFullName() : "-" %></td>
        <td><%= u.getUsername() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3" align="center">
            <font color="red">No users found</font>
        </td>
    </tr>
    <%
        }
    %>

</table>

<br/>

<table align="center" cellpadding="10">
    <tr>
        <td align="center" bgcolor="#ffcccc">
            <a href="logout">
                <font color="red"><b>Logout</b></font>
            </a>
        </td>
    </tr>
</table>

</body>
</html>
