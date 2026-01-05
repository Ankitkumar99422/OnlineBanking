<%@ page import="java.util.List,com.onlinebank.model.Transaction" %>
<html><head><title>Transactions</title></head><body>
<h2>Transactions</h2>
<table border="1">
    <tr><th>ID</th><th>Type</th><th>Amount</th><th>Description</th><th>Date</th></tr>
    <%
        List<Transaction> txs = (List<Transaction>) request.getAttribute("transactions");
        if (txs != null) {
            for (Transaction t: txs) {
    %>
        <tr>
            <td><%= t.getId() %></td>
            <td><%= t.getType() %></td>
            <td><%= t.getAmount() %></td>
            <td><%= t.getDescription() %></td>
            <td><%= t.getCreatedAt() %></td>
        </tr>
    <%
            }
        }
    %>
</table>
<p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body></html>
