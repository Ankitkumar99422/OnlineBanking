<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login - OnlineBank</title></head>
<body>
<h2>Login</h2>
<form method="post" action="login">
    Username: <input name="username" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    <button type="submit">Login</button>
</form>
<p><a href="register.jsp">Register</a></p>
<p style="color:red"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
<p style="color:green"><%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %></p>
</body></html>
