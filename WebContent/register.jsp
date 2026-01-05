<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Register - OnlineBank</title></head>
<body>
<h2>Register</h2>
<form method="post" action="register">
    Full Name: <input name="fullName" required/><br/>
    Username: <input name="username" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    <button type="submit">Register</button>
</form>
<p><a href="login.jsp">Back to Login</a></p>
<p style="color:red"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
</body></html>
