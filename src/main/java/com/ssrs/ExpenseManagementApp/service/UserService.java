package com.ssrs.ExpenseManagementApp.service;

import com.ssrs.ExpenseManagementApp.dao.UserRepository;
import com.ssrs.ExpenseManagementApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> findUserById(String id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepository.findById(id);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> createUser(User user) {
        try {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<User> updateUser(User user) {
        Optional<User> userFromDb = userRepository.findById(user.getId());

        if (userFromDb.isPresent()) {
            User userObj = userFromDb.get();
            userObj.setFirstName(user.getFirstName());
            userObj.setLastName(user.getLastName());
            userObj.setPhone(user.getPhone());
            return new ResponseEntity<>(userRepository.save(userObj), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
