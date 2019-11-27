package com.conference.api.controller;

import com.conference.api.domain.User;
import com.conference.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alex Sharman 24 Nov 2019
 */

@RestController
@Validated
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{uid}")
    public User updateUser(@Valid @RequestBody User newUser, @PathVariable String login) {
        if (userService.checkUserRole("ADMIN")) {
            User currentUser = userService.findUserByLogin(login);
            if (currentUser != null) {
                currentUser.setName(newUser.getName());
                currentUser.setSurname(newUser.getSurname());
                return userService.updateUser(currentUser);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access forbidden.");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/")
    @Transactional
    public User newUser(@RequestBody @Valid User newUser) {
//        Probably a nicer way to do it ...
        if (userService.userExists(newUser.getLogin())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User already exists.");
        } else {
            return userService.saveUser(newUser);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{login}")
    public void deleteUser(@PathVariable String login) {
        if (userService.removeUser(login) == 1) {
            throw new ResponseStatusException(HttpStatus.OK, "User removed");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not removed! does not exist?");
        }
    }

}
