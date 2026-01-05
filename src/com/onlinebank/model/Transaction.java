package com.onlinebank.model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accNo;
    private String type;
    private double amount;
    private String description;
    private Timestamp createdAt;

    // getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAccNo() { return accNo; }
    public void setAccNo(int accNo) { this.accNo = accNo; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
