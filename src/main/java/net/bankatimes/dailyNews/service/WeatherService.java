package net.bankatimes.dailyNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// import net.bankatimes.dailyNews.api.response.WeatherResponses;
import net.bankatimes.dailyNews.api.response.WeatherResponse;

@Component
public class WeatherService {

    private static final String apiKey = "1ATJysW90wYgLLWJaRXoOBowUAAvD67Q";

    private static final String API = "http://dataservice.accuweather.com/currentconditions/v1/1-204108_1_AL/historical/24?apikey=API_KEY";

    public static String getApiKey() {
        return apiKey;
    }
    
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(){
        String finalApiCall = API.replace("API_KEY", getApiKey());

        ResponseEntity<WeatherResponse[]> response = restTemplate.getForEntity(finalApiCall, WeatherResponse[].class);
        
        WeatherResponse body = response.getBody()[0];

        return body;


    }
}
