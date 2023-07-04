package com.ssrs.ExpenseManagementApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Pattern(regexp="^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+$", message="Invalid format identified for provided email")
    private String id;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    private Timestamp updatedAt;

    @Column(name = "phone")
    @Pattern(regexp = "\\d{10}")
    private String phone;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String toString() {
        return "User [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", phone=" + phone + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
