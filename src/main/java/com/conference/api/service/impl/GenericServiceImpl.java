package com.conference.api.service.impl;

import com.conference.api.domain.Room;
import com.conference.api.domain.User;
import com.conference.api.repository.RoomsRepository;
import com.conference.api.repository.UserRepository;
import com.conference.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */

@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Override
    public User findByUsername(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User saveUser(User newUser) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Room> findAllRooms() {
        return roomsRepository.findAll();
    }

    public Room findByRoomName(String roomName) {
        return roomsRepository.findByRoomName(roomName);
    }



}
