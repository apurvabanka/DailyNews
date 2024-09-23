package net.bankatimes.dailyNews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("redis-15544.c232.us-east-1-2.ec2.redns.redis-cloud.com");
        config.setPort(15544);
        config.setPassword("nQ1nYgckFTPLm7wsbeIUNg7HGGWHDKUK");

        System.out.println(config);
        
        return new LettuceConnectionFactory(config);
    }
}
