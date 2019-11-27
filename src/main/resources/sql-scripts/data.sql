insert into tbl_roles (id, role_name, description) values (1, 'USER', 'Standard User - Has no admin rights');
insert into tbl_roles (id, role_name, description) values (2, 'ADMIN', 'Admin User - Has permission to perform admin tasks');

-- Users:
-- Name		Surname			Login			Password
-- John		Smith			jsmith			qwerty
-- Jane		Doe				jdoe			mySecret
-- admin	admin			admin			q1w2e3r4
insert into tbl_users (id, name, surname, password, login) values (1, 'John', 'Smith', '$2a$10$YuocmfFnmEAqJeK5redXz./ts9VBZRTjgHYX1yJcGsELyrJQAVOm.', 'jsmith');
insert into tbl_users (id, name, surname, password, login) values (2, 'Jane', 'Doe', '$2a$10$bpCvk9iJveqxvP4TqA8ioOH7PxWIlJQnOW7j1mqN3RQPOD4Ew4xUK', 'jdoe');
insert into tbl_users (id, name, surname, password, login) values (3, 'Admin', 'Admin', '$2a$10$QLmOi3ytVpyXy9WUm.VVYObb0dy6M12tambtETt3T/9zBRot5ck0K', 'admin');

insert into user_role(user_id, role_id) values(1,1);
insert into user_role(user_id, role_id) values(2,1);
insert into user_role(user_id, role_id) values(3,2);

-- Populate Rooms
insert into tbl_rooms(room_name, location, nr_seats, projector, phone_number) values ('Large Room', '1st floor', 10, 'yes', '22-22-22-22' );
insert into tbl_rooms(room_name, location, nr_seats, projector, phone_number) values ('Medium Room', '1st floor', 6, 'yes', '' );
insert into tbl_rooms(room_name, location, nr_seats, projector, phone_number) values ('Small Room', '2nd floor', 4, 'no', '' );

-- Populate Meetings
insert into tbl_meetings(id, meeting_name, room_name, start_date, end_date ) values
                    ( 1, 'First Meeting', 'Medium Room', {ts '2019-11-28 18:00:00.00'}, {ts '2019-11-28 19:00:00.00'} );

-- Populate meeting_user
insert into meeting_user(meeting_id, user_id) values (1, 1);

-- Populate meeting_room
insert into meeting_room(meeting_id, room_id) values (1, 2);
