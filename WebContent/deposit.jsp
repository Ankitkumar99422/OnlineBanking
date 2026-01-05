<%@ page session="true" %>
<html>
<head>
    <title>Deposit</title>
</head>
<body>

<h2>Deposit</h2>

<form method="post" action="deposit">
    Amount:
    <input
            type="number"
            name="amount"
            min="1"
            step="0.01"
            required
    />
    <br/><br/>

    <button type="submit">Deposit</button>
</form>

<p style="color:red">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>

<p><a href="dashboard.jsp">Back to Dashboard</a></p>

</body>
</html>
