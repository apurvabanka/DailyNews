package net.bankatimes.dailyNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// import net.bankatimes.dailyNews.api.response.WeatherResponses;

import net.bankatimes.dailyNews.api.response.WeatherResponse;
import net.bankatimes.dailyNews.cache.AppCache;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(){
        String finalApiCall = appCache.APP_CACHE.get("weather_api").replace("<api_key>", apiKey);

        ResponseEntity<WeatherResponse[]> response = restTemplate.getForEntity(finalApiCall, WeatherResponse[].class);
        
        WeatherResponse body = response.getBody()[0];

        return body;


    }
}
