Online Banking - Java Web (JSP + Servlets + JDBC)
------------------------------------------------
How to run:
1. Import this folder as a Dynamic Web Project in Eclipse (or use any IDE).
2. Add MySQL JDBC driver to WEB-INF/lib (mysql-connector-java.jar).
3. Create a MySQL database `bankdb` and run the included schema.sql.
4. Update DBConnection.java with your DB username/password if needed.
5. Deploy to Tomcat 9+.

Default DB config in DBConnection.java uses:
  jdbc:mysql://localhost:3306/bankdb
  user: root
  pass: password

Notes:
- This is a minimal, educational project matching the provided rubric.
- For production, do not store passwords in plain text; use hashing.
