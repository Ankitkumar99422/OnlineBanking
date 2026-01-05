package com.onlinebank.servlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;
import com.onlinebank.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // 🔒 Admin authentication check
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        try {
            BankDAO dao = new BankDAOImpl();
            List<User> users = dao.getAllUsers();

            req.setAttribute("users", users);
            req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
