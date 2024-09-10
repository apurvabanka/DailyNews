package net.bankatimes.dailyNews.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "app_cache")
@Getter
@Setter
public class NewsAppCache {

    private String key;
    private String value;
    
}
