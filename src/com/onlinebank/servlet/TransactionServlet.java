package com.onlinebank.servlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;
import com.onlinebank.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession s = req.getSession(false);
            if (s == null || s.getAttribute("accNo") == null) {
                resp.sendRedirect("login.jsp");
                return;
            }
            int accNo = (int) s.getAttribute("accNo");
            BankDAO dao = new BankDAOImpl();
            List<Transaction> list = dao.getTransactions(accNo);
            req.setAttribute("transactions", list);
            req.getRequestDispatcher("transactions.jsp").forward(req, resp);
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
