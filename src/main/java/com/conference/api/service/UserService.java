package com.conference.api.service;

import com.conference.api.domain.Role;
import com.conference.api.domain.User;
import com.conference.api.repository.RoleRepository;
import com.conference.api.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex Sharman 24 Nov 2019
 */

@Service("userService")
@Transactional
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User saveUser(User user) {
        User newUser = createNewUser(user);
        return userRepository.save(newUser);
    }

    private User createNewUser(User newUser) {
        int length = 16;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        newUser.setPassword(bCryptPasswordEncoder.encode(generatedString));
        Role userRole = roleRepository.findByRoleName("USER");
        newUser.setRoles(Arrays.asList(userRole));
        return newUser;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Integer removeUser(String login) {
        return userRepository.removeByLogin(login);
    }

    public User updateUser(User user) {
//        userRepository.save(user);
        return userRepository.save(user);
    }

    public boolean checkUserRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> ((GrantedAuthority) r).getAuthority().equals("ADMIN"));
        return hasUserRole;
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByLogin(authentication.getName());
        String currentUsername = user.getLogin();
        return currentUsername;
    }

    public List<String> getAllUsernames() {
        return userRepository.findAll().stream()
                .map(n -> n.getLogin())
                .collect(Collectors.toList());
    }

//    public String getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUid(authentication.getName());
//        String currentUsername = user.getLogin();
//        return userRepository.deleteByLogin(currentUsername).getLogin();
//    }

    public List<String> getCurrentUid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByLogin(authentication.getName());
        List<String> currentUsername = Arrays.asList(user.getLogin());
        return currentUsername;
    }

    public Boolean userExists(String login) {
        User user;
        try {
            userService.findUserByLogin(login).getLogin();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
