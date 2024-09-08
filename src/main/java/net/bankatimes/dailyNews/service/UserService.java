package net.bankatimes.dailyNews.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import net.bankatimes.dailyNews.entity.User;
import net.bankatimes.dailyNews.respository.UserRepository;

@Component
@Slf4j //instead of creating instance we can use Bean
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveNewUser(User user){
        userRepository.save(user);
    }
    

    public List<User> getAll(){
        log.info("Getting all users"); //changed from logger to log in-order to use the bean and not the instance
        log.warn("This is warning");
        log.error("This is error");
        log.debug("This is debug");
        log.trace("This is trace");
        return userRepository.findAll();
    }

    public void deleteNewsById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
