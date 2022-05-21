package com.example.plantbook.service;

import com.example.plantbook.entity.User;
import com.example.plantbook.logger.MyLogger;
import com.example.plantbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The User service.
 * Handles all the business logic for the User entity.
 */
@Service
public class UserService {
    private static final Logger LOGGER = MyLogger.getInstance();

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find all users.
     *
     * @return the list of found users
     */
    public List<User> findAll() {
        LOGGER.info("Finding all users");
        return userRepository.findAll();
    }

    /**
     * Save user.
     *
     * @param user the user to save
     * @return the user saved
     */
    public User save(User user) {
        LOGGER.info("Saving user");
        userRepository.save(user);
        return user;
    }

    /**
     * Find by username.
     *
     * @param username the username of the user we want to find
     * @return the user with the given username
     */
    public User findByUsername(String username) {
        LOGGER.info("Finding user with username {}", username);
        return userRepository.findByUsername(username);
    }

    /**
     * Find by id.
     *
     * @param id the id of the user we want to find
     * @return the user with the given id
     */
    public User findById(Long id) {
        LOGGER.info("Finding user with id {}", id);
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Find by email.
     *
     * @param email the email of the user we want to find
     * @return the user with the given email
     */
    public User findByEmail(String email) {
        LOGGER.info("Finding user with email {}", email);
        return userRepository.findByEmail(email);
    }

    /**
     * Add balance.
     *
     * @param user   the user we want to add money to
     * @param amount the amount we want to add
     */
    @Transactional
    public void addBalance(User user, Double amount) {
        LOGGER.info("Adding {}$ to user {}", amount, user.getUsername());
        Double balance = user.getBalance();
        balance += amount;
        user.setBalance(balance);
        userRepository.save(user);
    }

}
