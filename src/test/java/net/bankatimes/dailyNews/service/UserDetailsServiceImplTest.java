package net.bankatimes.dailyNews.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.assertions.Assertions;

import net.bankatimes.dailyNews.controller.UserDetailsServiceImpl;
import net.bankatimes.dailyNews.entity.User;
import net.bankatimes.dailyNews.respository.UserRepository;

public class UserDetailsServiceImplTest {


    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Deprecated
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("apur").password("pass").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("apur");
        Assertions.assertNotNull(user);
    }

}
