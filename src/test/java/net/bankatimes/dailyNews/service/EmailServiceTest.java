package net.bankatimes.dailyNews.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmailServiceTest {
    

    @Autowired
    EmailService emailService;

    @Test
    void testEmail(){
        emailService.sendEmail("appu.banka@gmail.com", "Hello", "Hello there, hope you are going good.");
    }
}
