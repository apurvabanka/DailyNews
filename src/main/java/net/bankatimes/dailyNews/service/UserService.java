package net.bankatimes.dailyNews.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import org.bson.types.ObjectId;

import net.bankatimes.dailyNews.entity.User;
import net.bankatimes.dailyNews.respository.UserRepository;

@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveNewUser(User user){
        userRepository.save(user);
    }
    

    public List<User> getAll(){
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
