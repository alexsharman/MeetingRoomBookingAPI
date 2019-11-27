package com.conference.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alex Sharman 23 Nov 2019
 */

@Getter
@Setter
@Entity
@Table(name = "tbl_rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Length(max = 50)
    @Column(name = "room_name",
            unique = true)
    private String roomName;

    @Length(max = 256)
    @Column(name = "location",
            nullable = true)
    private String location;

    @Max(100)
    @Column(name = "nr_seats",
            nullable = false)
    private Integer nrSeats;

    @Column(columnDefinition = "boolean default false",
            nullable = true)
    private Boolean projector;

    @Length(max = 100)
    @Column(name = "phone_number",
            nullable = true)
    private String phoneNumber;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "meeting_room", joinColumns
            = @JoinColumn(name = "room_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id",
                    referencedColumnName = "id"))
    private List<Meeting> meeting;

    public void addMeeting(Meeting meeting){
        this.meeting.add(meeting);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(roomName, room.roomName) &&
                Objects.equals(location, room.location) &&
                Objects.equals(nrSeats, room.nrSeats) &&
                Objects.equals(projector, room.projector) &&
                Objects.equals(phoneNumber, room.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName, location, nrSeats, projector, phoneNumber);
    }
}
