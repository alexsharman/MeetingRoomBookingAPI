package com.conference.api.service;

import com.conference.api.domain.Meeting;
import com.conference.api.domain.Room;
import com.conference.api.domain.User;
import com.conference.api.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created on 26 Nov 2019
 *
 * @author Alex Sharman
 */

@Service("meetingsService")
@Transactional
public class MeetingsService {

    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingsService meetingsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    public MeetingsService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting findMeetingById(Long id) {
        return meetingRepository.findById(id).get();
    }

    public Meeting findMeetingByName(String meetingName) {
        return meetingRepository.findByMeetingName(meetingName);
    }

    public Meeting saveMeeting(Meeting meeting) {

        return meetingRepository.save(createMeeting(meeting));
    }

    private Meeting createMeeting(Meeting newMeeting) {
        User user = userService.findUserByLogin(newMeeting.getUser().getLogin());
//        Room room = roomsService.findRoomByName(newMeeting.getRoomName());
        newMeeting.setMeetingName(newMeeting.getMeetingName());
        newMeeting.setStartDate(newMeeting.getStartDate());
        newMeeting.setEndDate(newMeeting.getEndDate());
        newMeeting.setUser(user);
        newMeeting.setRoomName(newMeeting.getRoomName());
        roomsService.findRoomByName(newMeeting.getRoomName()).addMeeting(newMeeting);
        return newMeeting;
    }

    public List<Meeting> findAll() {
        return meetingRepository.findAll();
    }

    public Integer removeMeeting(String meetingName) {
        return meetingRepository.removeByMeetingName(meetingName);
    }

    public Meeting updateMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Boolean meetingExists(String meetingName) {
        try {
            meetingRepository.findByMeetingName(meetingName).getMeetingName();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Meeting> findMeetingsByNameAndStartDate(String name, Date start, Date end) {
        return meetingRepository.findByStartDateGreaterThan( start);
    }

    public List<Meeting> findByRoomName(String roomName){
        return meetingRepository.findByRoomName(roomName);
    }


}
