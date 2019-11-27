package com.conference.api.controller;

import com.conference.api.domain.Meeting;
import com.conference.api.domain.Room;
import com.conference.api.domain.User;
import com.conference.api.service.MeetingsService;
import com.conference.api.service.RoomsService;
import com.conference.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Created on 26 Nov 2019
 *
 * @author Alex Sharman
 */

@RestController
@Validated
@RequestMapping("/api/v1/meetings")
public class MeetingsController {

    @Autowired
    MeetingsService meetingsService;

    @Autowired
    RoomsService roomsService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Meeting> listAllMeetings() {
        return meetingsService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/")
    public ResponseEntity<Meeting> newMeeting(@RequestBody @Valid Meeting newMeeting) {
        if (meetingsService.meetingExists(newMeeting.getMeetingName())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Meeting already exists.");
        } else {
            return new ResponseEntity<>(meetingsService.saveMeeting(newMeeting), HttpStatus.ACCEPTED);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}")
    public ResponseEntity<Meeting> updateMeeting(@Valid @RequestBody Meeting newMeeting, @PathVariable String name) {
        Meeting currentMeeting = meetingsService.findMeetingByName(name);
        if (currentMeeting != null) {
            User user = userService.findUserByLogin(newMeeting.getUser().getLogin());
            Room room = roomsService.findRoomByName(newMeeting.getRoomName());
            currentMeeting.setMeetingName(newMeeting.getMeetingName());
            currentMeeting.setStartDate(newMeeting.getStartDate());
            currentMeeting.setEndDate(newMeeting.getEndDate());
            currentMeeting.setUser(user);
//            currentMeeting.setRoomName(room);
            return new ResponseEntity<>(meetingsService.updateMeeting(currentMeeting), HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Meeting not found.");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{MeetingName}")
    public void deleteMeeting(@PathVariable String MeetingName) {
        if (meetingsService.removeMeeting(MeetingName) == 1) {
            throw new ResponseStatusException(HttpStatus.OK, "Meeting removed");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Meeting does not exist?");
        }
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Meeting> listAllMeetingsForRoomNameBetweenDates(@RequestBody Meeting meetingDetails, @PathVariable String name) {
        return meetingsService.findMeetingsByNameAndStartDate(name, meetingDetails.getStartDate(), meetingDetails.getEndDate());
    }

}
