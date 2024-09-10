package net.bankatimes.dailyNews.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.bankatimes.dailyNews.respository.UserRepositoryImpl;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepositoryImlp;

    @Test
    void testSaveNewUser(){
        Assertions.assertNotNull(userRepositoryImlp.getUserForSA());
    }
    
}
