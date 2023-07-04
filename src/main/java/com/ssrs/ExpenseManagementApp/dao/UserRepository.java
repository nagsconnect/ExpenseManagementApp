package com.ssrs.ExpenseManagementApp.dao;

import com.ssrs.ExpenseManagementApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
