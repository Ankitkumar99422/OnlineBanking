<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Admin Login - OnlineBank</title>
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">OnlineBank Admin Login</font>
</h2>

<form method="post" action="adminLogin">
    <table align="center" cellpadding="15" bgcolor="#f3e8ff" width="30%">

        <tr>
            <td colspan="2" align="center">
                <font color="#5a189a"><b>Admin Access</b></font>
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
            <td colspan="2" align="center" bgcolor="#ffcccc">
                <button type="submit">
                    <font color="red"><b>Login as Admin</b></font>
                </button>
            </td>
        </tr>

    </table>
</form>

<br/>

<p align="center">
    <font color="red">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </font>
</p>

<p align="center">
    <a href="login.jsp">
        <font color="#5a189a"><b>Back to User Login</b></font>
    </a>
</p>

</body>
</html>
