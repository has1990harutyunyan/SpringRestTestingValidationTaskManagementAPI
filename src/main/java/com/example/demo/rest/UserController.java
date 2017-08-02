package com.example.demo.rest;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/add", consumes = "application/json")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        userService.addUser(user);
        return ResponseEntity.ok("USER CREATED");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping()
    public ResponseEntity getAllUsers () {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody  User  user) {
        User currentUser = userService.getUserById(id);
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        userService.updateUser(currentUser);
        return ResponseEntity.ok("UPDATED");

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser (@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("DELETED");

    }
}
