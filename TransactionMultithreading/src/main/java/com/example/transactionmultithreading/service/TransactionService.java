package com.example.transactionmultithreading.service;

import com.example.transactionmultithreading.model.BankAccount;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TransactionService {
    private final Map<String, BankAccount> accounts = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    public TransactionService() {
        accounts.put("user1",new BankAccount("1",500));
        accounts.put("user2",new BankAccount("2",0));
    }
    public String withdraw(String userId, int amount){
        BankAccount account = accounts.get(userId);
        if(account == null){
            return "Account not found";
        }
        executor.submit(()->{
            synchronized(account){
                if(account.withdraw(amount)){
                    System.out.println("Withdraw Successful");
                }else{
                    System.out.println("Withdraw Failed");
                }
            }
        });
        return "Withdraw Successful";
    }
    public String deposit(String userId, int amount){
        BankAccount account = accounts.get(userId);
        if(account == null){
            return "Account not found";
        }
        executor.submit(()->{
            account.deposit(amount);
        System.out.println("Deposit Successful");
        });
return "Deposited Successful";
    }
    public BankAccount getBalance(String userId){
        return accounts.get(userId);
    }
}
