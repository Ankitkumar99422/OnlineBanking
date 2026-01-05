package com.onlinebank.servlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession s = req.getSession(false);
            if (s == null || s.getAttribute("accNo") == null) {
                resp.sendRedirect("login.jsp");
                return;
            }
            int accNo = (int) s.getAttribute("accNo");
            double amount = Double.parseDouble(req.getParameter("amount"));
            BankDAO dao = new BankDAOImpl();
            boolean ok = dao.withdraw(accNo, amount);
            if (ok) {
                resp.sendRedirect("dashboard.jsp");
            } else {
                req.setAttribute("error", "Withdraw failed (insufficient funds?)");
                req.getRequestDispatcher("withdraw.jsp").forward(req, resp);
            }
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
