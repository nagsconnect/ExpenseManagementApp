package com.ssrs.ExpenseManagementApp.service;

import com.ssrs.ExpenseManagementApp.dao.ExpenseRepository;
import com.ssrs.ExpenseManagementApp.dao.UserRepository;
import com.ssrs.ExpenseManagementApp.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<Expense>> findByUserId(String userId) {
        if (userRepository.findById(userId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    public ResponseEntity<Expense> createExpense(Expense expense) {
        try {
            return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
