<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Login - OnlineBank</title>
    <meta charset="UTF-8">
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">OnlineBank Login</font>
</h2>

<!-- USER LOGIN BOX -->
<form method="post" action="login">
    <table align="center" cellpadding="15" bgcolor="#f3e8ff" width="35%">
        <tr>
            <td colspan="2" align="center">
                <font color="#5a189a"><b>Secure User Login</b></font>
            </td>
        </tr>

        <tr>
            <td align="right"><b>Username :</b></td>
            <td align="left">
                <input type="text" name="username" required />
            </td>
        </tr>

        <tr>
            <td align="right"><b>Password :</b></td>
            <td align="left">
                <input type="password" name="password" required />
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center" bgcolor="#e0cfff">
                <button type="submit"><b>Login</b></button>
            </td>
        </tr>
    </table>
</form>

<br/>

<!-- REGISTER -->
<table align="center" cellpadding="10" width="35%">
    <tr>
        <td align="center" bgcolor="#e0cfff">
            <a href="register.jsp"><b>Create New Account</b></a>
        </td>
    </tr>
</table>

<br/>

<!-- 🔴 ADMIN LOGIN OPTION -->
<table align="center" cellpadding="10" width="35%">
    <tr>
        <td align="center" bgcolor="#ffcccc">
            <a href="adminLogin.jsp">
                <font color="red"><b>Login as Admin</b></font>
            </a>
        </td>
    </tr>
</table>

<br/>

<p align="center">
    <font color="red">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </font>
</p>

<p align="center">
    <font color="green">
        <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
    </font>
</p>

</body>
</html>
