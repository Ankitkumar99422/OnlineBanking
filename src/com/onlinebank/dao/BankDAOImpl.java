package com.onlinebank.dao;

import com.onlinebank.model.*;
import com.onlinebank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDAOImpl implements BankDAO {

    @Override
    public int registerUser(User u) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO users(username,password,full_name) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getFullName());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
        return -1;
    }

    @Override
    public int loginUser(String username, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT id FROM users WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt("id");
        return -1;
    }

    @Override
    public Account createAccount(int userId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO accounts(user_id,balance) VALUES (?,0)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Account a = new Account();
            a.setAccNo(rs.getInt(1));
            a.setUserId(userId);
            a.setBalance(0);
            return a;
        }
        return null;
    }

    @Override
    public Account getAccountByUserId(int userId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM accounts WHERE user_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Account a = new Account();
            a.setAccNo(rs.getInt("acc_no"));
            a.setUserId(rs.getInt("user_id"));
            a.setBalance(rs.getDouble("balance"));
            return a;
        }
        return null;
    }

    @Override
    public Account getAccountByAccNo(int accNo) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM accounts WHERE acc_no=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accNo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Account a = new Account();
            a.setAccNo(rs.getInt("acc_no"));
            a.setUserId(rs.getInt("user_id"));
            a.setBalance(rs.getDouble("balance"));
            return a;
        }
        return null;
    }

    @Override
    public boolean deposit(int accNo, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            String upd = "UPDATE accounts SET balance = balance + ? WHERE acc_no=?";
            PreparedStatement ps = con.prepareStatement(upd);
            ps.setDouble(1, amount);
            ps.setInt(2, accNo);
            int updated = ps.executeUpdate();

            String ins = "INSERT INTO transactions(acc_no,type,amount,description) VALUES (?,?,?,?)";
            PreparedStatement pt = con.prepareStatement(ins);
            pt.setInt(1, accNo);
            pt.setString(2, "CREDIT");
            pt.setDouble(3, amount);
            pt.setString(4, "Deposit");
            pt.executeUpdate();

            con.commit();
            return updated > 0;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean withdraw(int accNo, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            Account a = getAccountByAccNo(accNo);
            if (a == null || a.getBalance() < amount) return false;

            String upd = "UPDATE accounts SET balance = balance - ? WHERE acc_no=?";
            PreparedStatement ps = con.prepareStatement(upd);
            ps.setDouble(1, amount);
            ps.setInt(2, accNo);
            int updated = ps.executeUpdate();

            String ins = "INSERT INTO transactions(acc_no,type,amount,description) VALUES (?,?,?,?)";
            PreparedStatement pt = con.prepareStatement(ins);
            pt.setInt(1, accNo);
            pt.setString(2, "DEBIT");
            pt.setDouble(3, amount);
            pt.setString(4, "Withdrawal");
            pt.executeUpdate();

            con.commit();
            return updated > 0;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean transfer(int fromAcc, int toAcc, double amount, String desc) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            Account from = getAccountByAccNo(fromAcc);
            Account to = getAccountByAccNo(toAcc);

            if (from == null || to == null || from.getBalance() < amount) return false;

            PreparedStatement pw = con.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE acc_no=?");
            pw.setDouble(1, amount);
            pw.setInt(2, fromAcc);
            pw.executeUpdate();

            PreparedStatement pd = con.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE acc_no=?");
            pd.setDouble(1, amount);
            pd.setInt(2, toAcc);
            pd.executeUpdate();

            String ins = "INSERT INTO transactions(acc_no,type,amount,description) VALUES (?,?,?,?)";

            PreparedStatement pt1 = con.prepareStatement(ins);
            pt1.setInt(1, fromAcc);
            pt1.setString(2, "DEBIT");
            pt1.setDouble(3, amount);
            pt1.setString(4, desc != null ? desc : "Transfer to " + toAcc);
            pt1.executeUpdate();

            PreparedStatement pt2 = con.prepareStatement(ins);
            pt2.setInt(1, toAcc);
            pt2.setString(2, "CREDIT");
            pt2.setDouble(3, amount);
            pt2.setString(4, desc != null ? desc : "Transfer from " + fromAcc);
            pt2.executeUpdate();

            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public List<Transaction> getTransactions(int accNo) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM transactions WHERE acc_no=? ORDER BY created_at DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accNo);
        ResultSet rs = ps.executeQuery();

        List<Transaction> list = new ArrayList<>();
        while (rs.next()) {
            Transaction t = new Transaction();
            t.setId(rs.getInt("id"));
            t.setAccNo(rs.getInt("acc_no"));
            t.setType(rs.getString("type"));
            t.setAmount(rs.getDouble("amount"));
            t.setDescription(rs.getString("description"));
            t.setCreatedAt(rs.getTimestamp("created_at"));
            list.add(t);
        }
        return list;
    }

    // ✅ ADMIN FEATURE
    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT u.id, u.full_name, u.username, a.balance " +
                        "FROM users u LEFT JOIN accounts a ON u.id = a.user_id";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setFullName(rs.getString("full_name"));
            u.setUsername(rs.getString("username"));

            // balance may be NULL if account not created
            u.setBalance(rs.getDouble("balance"));

            users.add(u);
        }
        return users;
    }

}
