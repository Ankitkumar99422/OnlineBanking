package com.onlinebank.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Simple hardcoded admin check (DB version can be added later)
        if ("admin".equals(username) && "admin123".equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("admin", true);

            resp.sendRedirect("adminDashboard");
        } else {
            req.setAttribute("error", "Invalid admin credentials");
            req.getRequestDispatcher("adminLogin.jsp").forward(req, resp);
        }
    }
}
