package net.bankatimes.dailyNews.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.bankatimes.dailyNews.respository.UserRepository;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsEntryService newsEntryService;

    @Test
    public void testAdd(){
        
        assertNotNull(userRepository.findByUsername("apurv"));
    }

    @ParameterizedTest
    @CsvSource({
        "apurv",
        "apur"
    })
    public void test(String username){
       assertNotNull(userRepository.findByUsername(username));
    }
    
}
