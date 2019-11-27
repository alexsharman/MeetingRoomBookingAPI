package service;

import com.conference.api.BookingApiApplication;
import com.conference.api.domain.User;
import com.conference.api.repository.UserRepository;
import com.conference.api.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApiApplication.class)
public class UserServiceTestSuite {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private User user;

    @Before
    public void makeTestUser() {
        user.setName("test");
        user.setSurname("TestSurname");
        user.setLogin("tuser");
        user.setPassword("q123456das");
    }

    @Test
    public void checkInitialUsersLoaded() {
        //given
        //when
        //then
        assertThat(userRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void createNewUser() {
        //given

        //when
        User newUser = userRepository.save(user);
        //then
        assertThat(newUser).isEqualTo(user);
    }

    @Test
    public void deleteUser() {
        //given

        //when
        userRepository.delete(user);
        //then
        assertThat(userRepository.findByLogin(user.getLogin())).isNull();
    }

}
