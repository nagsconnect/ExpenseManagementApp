package com.ssrs.ExpenseManagementApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "is_debit", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isDebit = true;

    @Column(name = "category")
    private String category = "NONE";

    @Column(name = "description")
    private String description;

    @Column(name = "user_id", nullable = false)
    @Pattern(regexp="^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+$", message="Invalid format identified for provided email")
    private String userId;

    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", amount=" + amount +
                ", isDebit=" + isDebit +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
