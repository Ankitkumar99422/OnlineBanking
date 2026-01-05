<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Register - OnlineBank</title>
    <meta charset="UTF-8">
</head>

<body bgcolor="#f2f5f9">

<h2 align="center">
    <font color="#4b0082">Create OnlineBank Account</font>
</h2>

<form method="post" action="register">
    <table align="center" cellpadding="15" bgcolor="#f3e8ff" width="40%">

        <tr>
            <td colspan="2" align="center">
                <font color="#5a189a"><b>Registration Form</b></font>
            </td>
        </tr>

        <tr>
            <td align="right"><b>Full Name :</b></td>
            <td align="left">
                <input type="text" name="fullName" required />
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
                <button type="submit"><b>Register</b></button>
            </td>
        </tr>

    </table>
</form>

<br/>

<table align="center" cellpadding="10" width="40%">
    <tr>
        <td align="center" bgcolor="#e0cfff">
            <a href="login.jsp"><b>Back to Login</b></a>
        </td>
    </tr>
</table>

<p align="center">
    <font color="red">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </font>
</p>

</body>
</html>
