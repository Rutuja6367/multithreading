package com.example.transactionmultithreading.controller;

import com.example.transactionmultithreading.model.BankAccount;
import com.example.transactionmultithreading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String id, @RequestParam int amount) {
        return transactionService.withdraw(id,amount);
    }
    @PostMapping("/deposit")
    public String deposit(@RequestParam String id, @RequestParam int amount) {
        return transactionService.deposit(id,amount);
    }
    @GetMapping("/balance/{id}")
    public BankAccount getBalance(@PathVariable String id){
        return transactionService.getBalance(id);
    }
}
