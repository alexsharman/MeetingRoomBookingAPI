package com.conference.api.controller;

import com.conference.api.domain.Room;
import com.conference.api.domain.User;
import com.conference.api.service.GenericService;
import com.conference.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */

@Transactional
@RestController
@RequestMapping("/api/v1")
public class ResourceController {
    @Autowired
    private UserService userService;

    @Autowired
    private GenericService roomsService;

    @RequestMapping(value ="/rooms")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Room> getUser(){
        return roomsService.findAllRooms();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsers(){
        return userService.findAll();
    }
}
