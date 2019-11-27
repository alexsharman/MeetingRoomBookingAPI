package com.conference.api.controller;

import com.conference.api.domain.Room;
import com.conference.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @Autowired
    GenericService roomsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Room> listAllRooms() {
        return roomsService.findAllRooms();
    }
}
