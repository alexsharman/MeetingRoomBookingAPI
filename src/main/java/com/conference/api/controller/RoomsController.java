package com.conference.api.controller;

import com.conference.api.domain.Meeting;
import com.conference.api.domain.Room;
import com.conference.api.service.GenericService;
import com.conference.api.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @Autowired
    GenericService roomsService;

    @Autowired
    MeetingsService meetingsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> listAllRooms() {
        return new ResponseEntity(roomsService.findAllRooms(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{roomName}", method = RequestMethod.GET)
    public ResponseEntity<Room> findRoomByName (@PathVariable String roomName) {
        return new ResponseEntity(roomsService.findByRoomName(roomName), HttpStatus.OK);
    }

    @RequestMapping(value = "/{roomName}", method = RequestMethod.GET)
    public ResponseEntity<List<Meeting>> listAllMeetingForRoomInDates(@PathVariable String roomName, @PathVariable Date startDate, @PathVariable Date endDate) {
        return new ResponseEntity(meetingsService.findByRoomName(roomName), HttpStatus.OK);
    }

}
