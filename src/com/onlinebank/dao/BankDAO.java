package com.onlinebank.dao;

import com.onlinebank.model.*;
import java.util.List;

public interface BankDAO {
    int registerUser(User u) throws Exception;
    int loginUser(String username, String password) throws Exception;
    Account createAccount(int userId) throws Exception;
    Account getAccountByUserId(int userId) throws Exception;
    Account getAccountByAccNo(int accNo) throws Exception;
    boolean deposit(int accNo, double amount) throws Exception;
    boolean withdraw(int accNo, double amount) throws Exception;
    boolean transfer(int fromAcc, int toAcc, double amount, String desc) throws Exception;
    List<Transaction> getTransactions(int accNo) throws Exception;
    List<User> getAllUsers() throws Exception;

}
