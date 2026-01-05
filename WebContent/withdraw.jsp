<%@ page session="true" %>
<html>
<head>
    <title>Withdraw</title>
</head>
<body>

<h2>Withdraw</h2>

<form method="post" action="withdraw">
    Amount:
    <input
            type="number"
            name="amount"
            min="1"
            step="0.01"
            required
    />
    <br/><br/>

    <button type="submit">Withdraw</button>
</form>

<p style="color:red">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>

<p><a href="dashboard.jsp">Back to Dashboard</a></p>

</body>
</html>
