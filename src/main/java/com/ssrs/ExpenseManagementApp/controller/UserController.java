package com.ssrs.ExpenseManagementApp.controller;

import com.ssrs.ExpenseManagementApp.model.Expense;
import com.ssrs.ExpenseManagementApp.model.User;
import com.ssrs.ExpenseManagementApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return userService.findUserById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registerUser(@ModelAttribute User user, Model model) {
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();
        ResponseEntity<User> responseEntity = userService.createUser(user);
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            model.addAttribute("message", "User registered successfully");
            modelAndView.setViewName("success");
            return modelAndView;
        } else {
            model.addAttribute("message", "Failed to register user. Please try again.");
            model.addAttribute("responseEntity", responseEntity);
            modelAndView.setViewName("success");
            return modelAndView;
        }
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
