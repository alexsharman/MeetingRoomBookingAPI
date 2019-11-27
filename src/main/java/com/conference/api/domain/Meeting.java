package com.conference.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created on 26 Nov 2019
 *
 * @author Alex Sharman
 */

@Getter
@Setter
@Valid
@Entity
@Table(name = "tbl_meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "meeting_name")
    private String meetingName;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "meeting_user", joinColumns
            = @JoinColumn(name = "meeting_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"))
    private User user;

    @NotNull
    @Column(name = "room_name")
    private String roomName;

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    private Date endDate;


}

