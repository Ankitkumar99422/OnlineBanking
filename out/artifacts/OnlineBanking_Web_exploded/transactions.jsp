<%@ page import="java.util.List,com.onlinebank.model.Transaction" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Transaction> txs = (List<Transaction>) request.getAttribute("transactions");
%>

<html>
<head>
    <title>Transactions</title>
    <meta charset="UTF-8">
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">Transaction History</font>
</h2>

<table align="center" cellpadding="10" bgcolor="#f3e8ff" border="1">
    <tr bgcolor="#e0cfff">
        <th>ID</th>
        <th>Type</th>
        <th>Amount</th>
        <th>Description</th>
        <th>Date</th>
    </tr>

    <%
        if (txs != null && !txs.isEmpty()) {
            for (Transaction t : txs) {
    %>
    <tr align="center">
        <td><%= t.getId() %></td>
        <td><%= t.getType() %></td>
        <td>₹ <%= t.getAmount() %></td>
        <td><%= t.getDescription() != null ? t.getDescription() : "-" %></td>
        <td><%= t.getCreatedAt() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5" align="center">
            <font color="red">No transactions found</font>
        </td>
    </tr>
    <%
        }
    %>

</table>

<br/>

<p align="center">
    <a href="dashboard.jsp">
        <font color="#5a189a">Back to Dashboard</font>
    </a>
</p>

</body>
</html>
