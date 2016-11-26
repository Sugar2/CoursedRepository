package com.coursed.service;

import com.coursed.dto.UserRegistrationForm;
import com.coursed.model.auth.Role;
import com.coursed.model.auth.User;
import com.coursed.repository.RoleRepository;
import com.coursed.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Hexray on 06.11.2016.
 * Edited by Trach on 07.11.2016
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void register(User user) {
        user.setStudent(false);
        user.setTeacher(false);
        user.setEmailConfirmed(false);
        user.setRoleConfirmed(false);
        user.setRegistrationDate(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        LOGGER.debug("Saving user with email={}", user.getEmail().replaceFirst("@.*", "@***"));
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findOne(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> findAll() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public void addRole(Long userId, Long roleId) {
        //TODO: log it
        Role role = roleRepository.findOne(roleId);
        User user = userRepository.findOne(userId);

        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
    }


}
