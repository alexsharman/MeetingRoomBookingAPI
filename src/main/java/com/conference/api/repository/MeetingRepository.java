package com.conference.api.repository;

import com.conference.api.domain.Meeting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created on 26 Nov 2019
 *
 * @author Alex Sharman
 */

public interface MeetingRepository extends CrudRepository<Meeting, Long> {

    Meeting findByMeetingName(String meetingName);

    List<Meeting> findAll();

    Integer removeByMeetingName(String meetingName);

    List<Meeting> findByRoomName(String roomName);

    List<Meeting> findByStartDateGreaterThan(Date start);



}
