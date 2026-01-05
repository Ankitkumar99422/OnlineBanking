package com.onlinebank.servlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;
import com.onlinebank.model.User;
import com.onlinebank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String fullName = req.getParameter("fullName");

            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            u.setFullName(fullName);

            BankDAO dao = new BankDAOImpl();
            int userId = dao.registerUser(u);
            if (userId > 0) {
                // create default account
                Account acc = dao.createAccount(userId);
                req.setAttribute("msg", "Registered successfully. Your account no: " + acc.getAccNo());
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Registration failed");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
