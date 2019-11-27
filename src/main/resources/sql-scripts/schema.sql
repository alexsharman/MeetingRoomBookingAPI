
create TABLE tbl_roles (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


create TABLE tbl_users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  surname varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  login varchar(100) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);


create TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES tbl_users (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES tbl_users (id)
);

create TABLE tbl_rooms (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    room_name varchar(50) NOT NULL UNIQUE,
    location varchar(256) ,
    nr_seats int(100) NOT NULL,
    projector varchar(3),
    phone_number varchar(100),
    PRIMARY KEY (id)
    );

create TABLE tbl_meetings (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    meeting_name varchar(256) NOT NULL,
    room_name varchar(256) NOT NULL,
    start_date timestamp NOT NULL,
    end_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

create TABLE meeting_user (
id bigint(20) NOT NULL AUTO_INCREMENT,
  meeting_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  CONSTRAINT constntuid FOREIGN KEY (user_id) REFERENCES tbl_users (id),
  CONSTRAINT constntmeetid FOREIGN KEY (meeting_id) REFERENCES tbl_meetings (id),
  PRIMARY KEY (id)
);

create TABLE meeting_room (
id bigint(20) NOT NULL AUTO_INCREMENT,
  meeting_id bigint(20) NOT NULL,
  room_id bigint(20) NOT NULL,
  CONSTRAINT constntmeetid2 FOREIGN KEY (meeting_id) REFERENCES tbl_meetings (id),
  CONSTRAINT constntroomid2 FOREIGN KEY (room_id) REFERENCES tbl_rooms (id),
  PRIMARY KEY (id)
);

create sequence HIBERNATE_SEQUENCE start with 1 increment by 1;