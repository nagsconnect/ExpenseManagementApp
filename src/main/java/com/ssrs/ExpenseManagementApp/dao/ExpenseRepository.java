package com.ssrs.ExpenseManagementApp.dao;

import com.ssrs.ExpenseManagementApp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
    List<Expense> findByUserId(String userId);
}
