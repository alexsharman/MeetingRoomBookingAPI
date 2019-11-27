package com.conference.api.repository;

import com.conference.api.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Alex Sharman 22 Nov 2019
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    List<User> findAll();

    Integer removeByLogin(String login);

}
