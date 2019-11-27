package com.conference.api.controller;

import com.conference.api.domain.Room;
import com.conference.api.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */

@RestController
@Validated
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @Autowired
    RoomsService roomsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> listAllRooms() {
        return new ResponseEntity(roomsService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{roomName}", method = RequestMethod.GET)
    public ResponseEntity<Room> findRoomsByName (@PathVariable String roomName) {
        return new ResponseEntity(roomsService.findRoomByName(roomName), HttpStatus.OK);
    }
}
