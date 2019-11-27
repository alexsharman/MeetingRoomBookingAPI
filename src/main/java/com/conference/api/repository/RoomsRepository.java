package com.conference.api.repository;

import com.conference.api.domain.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Alex Sharman 23 Nov 2019
 */

public interface RoomsRepository extends CrudRepository<Room, Long> {
    Room findByRoomName(String roomName);

    Integer removeByRoomName(String roomName);

    List<Room> findAll();
}
