<%@ page session="true" %>
<html>
<head><title>Transfer</title></head>
<body>
<h2>Transfer</h2>
<form method="post" action="transfer">
    Recipient Account No:
    <input
            type="text"
            name="toAcc"
            required
            pattern="[0-9]+"
            title="Enter a valid account number"
    /><br/><br/>

    Amount:
    <input
            type="text"
            name="amount"
            required
            pattern="[0-9]+(\.[0-9]{1,2})?"
            title="Enter a valid amount. Example: 100 or 100.50"
    /><br/><br/>

    <button type="submit">Transfer</button>
</form>
<p style="color:red">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>
<p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
