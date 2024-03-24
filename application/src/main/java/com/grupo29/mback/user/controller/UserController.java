package com.grupo29.mback.user.controller;

import com.grupo29.mback.user.entities.User;
import com.grupo29.mback.user.exception.UserException;
import com.grupo29.mback.user.usecase.CreateUserUseCase;
import com.grupo29.mback.user.usecase.DeleteUserUseCase;
import com.grupo29.mback.user.usecase.FindUserUseCase;
import com.grupo29.mback.user.usecase.UpdateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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
    public User getUserById(@PathVariable Long id) throws UserException {
        return findUserUseCase.execute(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws UserException {
        return createUserUseCase.execute(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) throws UserException {
        return updateUserUseCase.execute(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
    }
}
