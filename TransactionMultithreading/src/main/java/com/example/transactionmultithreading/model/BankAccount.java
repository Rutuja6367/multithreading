package com.example.transactionmultithreading.model;

public class BankAccount {
    String id;
    private int balance;
    public BankAccount(String id, int balance){
        this.id = id;
        this.balance = balance;
    }
    public String getId() {
        return id;
    }
    public int getBalance() {
        return balance;
    }
    public synchronized boolean withdraw(int amount){
        if(amount >= balance){
            balance -= amount;
            return true;
        }
        return false;
    }
    public synchronized void deposit(int amount){
        balance += amount;
    }

}
