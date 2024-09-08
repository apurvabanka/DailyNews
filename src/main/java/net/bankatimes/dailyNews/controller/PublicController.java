package net.bankatimes.dailyNews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bankatimes.dailyNews.entity.User;
import net.bankatimes.dailyNews.api.response.WeatherResponse;
import net.bankatimes.dailyNews.service.UserService;
import net.bankatimes.dailyNews.service.WeatherService;

@RestController
@RequestMapping("/public")
public class PublicController {

     @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @GetMapping
    public String greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WeatherResponse weather = weatherService.getWeather();
        String output = "Hi " + username + " the weather is " + weather.getWeatherText() + " and the temperatur is " + weather.getTemperature().getMetric().getMetricValue() + " " + weather.getTemperature().getMetric().getMetricUnit(); 
        return output;
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createEntry(@RequestBody User user){

        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }


}
