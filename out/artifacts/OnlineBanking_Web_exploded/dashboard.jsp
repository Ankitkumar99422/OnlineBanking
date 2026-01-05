<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.onlinebank.dao.*,com.onlinebank.model.*" %>
<%@ page session="true" %>

<%
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Integer accNo = (Integer) session.getAttribute("accNo");
    BankDAO dao = new BankDAOImpl();
    Account acc = null;

    if (accNo != null) {
        acc = dao.getAccountByAccNo(accNo);
    }
%>

<html>
<head>
    <title>Dashboard</title>
    <meta charset="UTF-8">
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">Kangaal Bank :)</font>
</h2>

<!-- ACCOUNT SUMMARY BOX -->
<table align="center" cellpadding="15" bgcolor="#f3e8ff" width="50%">
    <tr>
        <td align="center" colspan="2">
            <font color="#5a189a"><b>Account Summary</b></font>
        </td>
    </tr>
    <tr>
        <td align="right"><b>Account No :</b></td>
        <td align="left"><%= acc != null ? acc.getAccNo() : "N/A" %></td>
    </tr>
    <tr>
        <td align="right"><b>Balance :</b></td>
        <td align="left">₹ <%= acc != null ? acc.getBalance() : "N/A" %></td>
    </tr>
</table>

<br/>

<!-- ACTION BUTTONS -->
<table align="center" cellpadding="12" width="60%">
    <tr>
        <td align="center" bgcolor="#e0cfff">
            <a href="deposit.jsp"><b>Deposit</b></a>
        </td>
        <td align="center" bgcolor="#e0cfff">
            <a href="withdraw.jsp"><b>Withdraw</b></a>
        </td>
        <td align="center" bgcolor="#e0cfff">
            <a href="transfer.jsp"><b>Transfer</b></a>
        </td>
    </tr>
    <tr>
        <td align="center" bgcolor="#e0cfff">
            <a href="transactions"><b>Transactions</b></a>
        </td>
        <td align="center" bgcolor="#ffcccc">
            <a href="logout"><b>Logout</b></a>
        </td>
        <td align="center">
            <!-- empty for balance -->
        </td>
    </tr>
</table>

</body>
</html>
