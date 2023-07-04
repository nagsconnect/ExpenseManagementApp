package com.ssrs.ExpenseManagementApp.controller;

import com.ssrs.ExpenseManagementApp.model.Expense;
import com.ssrs.ExpenseManagementApp.model.User;
import com.ssrs.ExpenseManagementApp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/form")
    public ModelAndView showExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("expense-form");
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView createExpense(@ModelAttribute Expense expense, Model model) {
        ResponseEntity<Expense> responseEntity = expenseService.createExpense(expense);
        ModelAndView modelAndView = new ModelAndView();
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            model.addAttribute("message", "Expense saved successfully.");
            modelAndView.setViewName("success");
            return modelAndView;
        } else {
            model.addAttribute("message", "Failed to save expense. Please try again.");
            modelAndView.getModel().put("responseEntity", responseEntity);
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

    @GetMapping("/view")
    public ModelAndView getExpenses(Model model) {
        model.addAttribute("userId", new User());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("expense-view-form");
        return modelAndView;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable String userId, Model model) {
        System.out.println(userId);
        return expenseService.findByUserId(userId);
    }
}
