package com.onlinebank.servlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession s = req.getSession(false);
            if (s == null || s.getAttribute("accNo") == null) {
                resp.sendRedirect("login.jsp");
                return;
            }
            int fromAcc = (int) s.getAttribute("accNo");
            int toAcc = Integer.parseInt(req.getParameter("toAcc"));
            double amount = Double.parseDouble(req.getParameter("amount"));
            String desc = req.getParameter("desc");
            BankDAO dao = new BankDAOImpl();
            boolean ok = dao.transfer(fromAcc, toAcc, amount, desc);
            if (ok) resp.sendRedirect("dashboard.jsp");
            else {
                req.setAttribute("error", "Transfer failed");
                req.getRequestDispatcher("transfer.jsp").forward(req, resp);
            }
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
