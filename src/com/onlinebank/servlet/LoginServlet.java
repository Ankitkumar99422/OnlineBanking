package com.onlinebank.servlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;
import com.onlinebank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            BankDAO dao = new BankDAOImpl();
            int userId = dao.loginUser(username, password);
            if (userId > 0) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", userId);
                Account acc = dao.getAccountByUserId(userId);
                session.setAttribute("accNo", acc != null ? acc.getAccNo() : null);
                resp.sendRedirect("dashboard.jsp");
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
