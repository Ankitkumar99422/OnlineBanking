import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import com.onlinebank.dao.BankDAO;
import com.onlinebank.dao.BankDAOImpl;

import java.io.IOException;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // ✅ AUTH CHECK (userId)
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // ✅ BUSINESS DATA (accNo)
        Integer accNoObj = (Integer) session.getAttribute("accNo");
        if (accNoObj == null) {
            req.setAttribute("error", "Account not found. Please contact support.");
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            return;
        }

        int accNo = accNoObj;
        String amountStr = req.getParameter("amount");

        try {
            double amount = Double.parseDouble(amountStr);

            if (amount <= 0) {
                req.setAttribute("error", "Amount must be positive");
                req.getRequestDispatcher("deposit.jsp").forward(req, resp);
                return;
            }

            BankDAO dao = new BankDAOImpl();
            boolean ok = dao.deposit(accNo, amount);

            if (ok) {
                resp.sendRedirect("dashboard.jsp");
            } else {
                req.setAttribute("error", "Deposit failed");
                req.getRequestDispatcher("deposit.jsp").forward(req, resp);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid amount format");
            req.getRequestDispatcher("deposit.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
