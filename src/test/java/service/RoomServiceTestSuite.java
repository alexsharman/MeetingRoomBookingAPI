package service;

import com.conference.api.BookingApiApplication;
import com.conference.api.domain.Room;
import com.conference.api.repository.RoomsRepository;
import com.conference.api.service.RoomsService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApiApplication.class)
public class RoomServiceTestSuite {

    @Autowired
    RoomsService roomService;

    @Autowired
    RoomsRepository roomRepository;

    private Room room;

    @BeforeAll
    public void makeTestRoom() {
        room.setRoomName("testRoom");
        room.setLocation("testLocation");
        room.setNrSeats(14);
        room.setProjector(false);
        room.setPhoneNumber("1234564564");
    }

    @Test
    public void checkInitialRoomsLoaded() {
        //given
        //when
        //then
        assertThat(roomRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void createNewRoom() {
        //given
        room.setRoomName("testRoom");
        room.setLocation("testLocation");
        room.setNrSeats(14);
        room.setProjector(false);
        room.setPhoneNumber("1234564564");
        //when
        Room newRoom = roomRepository.save(room);
        //then
        assertThat(newRoom).isEqualTo(room);
    }

    @Test
    public void deleteRoom() {
        //given
        room.setRoomName("testRoom");
        room.setLocation("testLocation");
        room.setNrSeats(14);
        room.setProjector(false);
        room.setPhoneNumber("1234564564");
        //when
        roomRepository.delete(room);
        //then
        assertThat(roomRepository.findByRoomName(room.getRoomName())).isNull();
    }

}
