<%@ page import="com.onlinebank.dao.BankDAO,com.onlinebank.dao.BankDAOImpl,com.onlinebank.model.Account" %>
<%@ page session="true" %>
<%
    Integer userId = (Integer) session.getAttribute("userId");
    Integer accNo = (Integer) session.getAttribute("accNo");
    if (userId == null) { response.sendRedirect("login.jsp"); return; }
    BankDAO dao = new BankDAOImpl();
    Account acc = null;
    try { acc = dao.getAccountByAccNo(accNo); } catch(Exception e){ }
%>
<html><head><title>Dashboard</title></head><body>
<h2>Dashboard</h2>
<p>Account No: <%= acc != null ? acc.getAccNo() : "N/A" %></p>
<p>Balance: <%= acc != null ? acc.getBalance() : "N/A" %></p>
<p>
    <a href="deposit.jsp">Deposit</a> |
    <a href="withdraw.jsp">Withdraw</a> |
    <a href="transfer.jsp">Transfer</a> |
    <a href="transactions">Transactions</a> |
    <a href="logout">Logout</a>
</p>
</body></html>
