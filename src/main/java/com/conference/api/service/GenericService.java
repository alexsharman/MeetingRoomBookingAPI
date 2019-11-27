package com.conference.api.service;

import com.conference.api.domain.Room;
import com.conference.api.domain.User;

import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */

public interface GenericService {

    User findByUsername(String username);
    User saveUser(User newUser);
    List<User> findAllUsers();

    List<Room> findAllRooms();
    Room findByRoomName(String roomName);

}
