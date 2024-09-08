package net.bankatimes.dailyNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class DailyNewsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DailyNewsApplication.class, args);
		System.out.println(context.getEnvironment());
	}

	@Bean
	public PlatformTransactionManager transactionFunction(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

//PlatformTransactionManager - An interface called by EableTransactionManager
//MongoTranactionManager - A mongoDB interface to implement transaction