package com.grupo29.mback.user.controller;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.usecase.CreateUserUseCase;
import com.grupo29.mback.user.usecase.DeleteUserUseCase;
import com.grupo29.mback.user.usecase.FindUserUseCase;
import com.grupo29.mback.user.usecase.UpdateUserUseCase;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Log4j2
public class UserController {
    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private FindUserUseCase findUserUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(findUserUseCase.execute(id));
        } catch (UserException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(createUserUseCase.execute(user));
        } catch (UserException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            return ResponseEntity.ok(updateUserUseCase.execute(id, userDetails));
        } catch (UserException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
