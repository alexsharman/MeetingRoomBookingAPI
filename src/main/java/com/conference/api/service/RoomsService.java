package com.conference.api.service;

import com.conference.api.domain.Room;
import com.conference.api.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alex Sharman 26 Nov 2019
 */

@Service("roomsService")
@Transactional
public class RoomsService {

    private RoomsRepository roomsRepository;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public Room findRoomById(Long id) {
        return roomsRepository.findById(id).get();
    }

    public Room findRoomByName(String roomName) {
        return roomsRepository.findByRoomName(roomName);
    }

    public Room saveRoom(Room newRoom) {
        return roomsRepository.save(newRoom);
    }

    public List<Room> findAll() {
        return roomsRepository.findAll();
    }

    public Integer removeRoom(String roomName) {
        return roomsRepository.removeByRoomName(roomName);
    }

    public Room updateRoom(Room room) {
        return roomsRepository.save(room);
    }

    public Boolean roomExists(String roomName) {
        try {
            roomsService.findRoomByName(roomName).getRoomName();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
