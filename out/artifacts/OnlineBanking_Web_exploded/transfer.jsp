<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Transfer</title>
    <meta charset="UTF-8">
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">Transfer Money</font>
</h2>

<form method="post" action="transfer">
    <table align="center" cellpadding="10" bgcolor="#f3e8ff">
        <tr>
            <td align="right"><b>To Account No :</b></td>
            <td align="left">
                <input type="number" name="toAcc" min="1" required />
            </td>
        </tr>

        <tr>
            <td align="right"><b>Amount :</b></td>
            <td align="left">
                <input type="number" name="amount" min="1" step="0.01" required />
            </td>
        </tr>

        <tr>
            <td align="right"><b>Description :</b></td>
            <td align="left">
                <input type="text" name="desc" />
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <button type="submit">Transfer</button>
            </td>
        </tr>
    </table>
</form>

<p align="center">
    <font color="red">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </font>
</p>

<p align="center">
    <a href="dashboard.jsp">
        <font color="#5a189a">Back to Dashboard</font>
    </a>
</p>

</body>
</html>
