package net.bankatimes.dailyNews.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import net.bankatimes.dailyNews.entity.NewsAppCache;
import net.bankatimes.dailyNews.respository.AppCacheRepository;

@Component
public class AppCache {

    public Map<String, String> APP_CACHE = new HashMap<>();

    @Autowired
    private AppCacheRepository appCacheRepository;
    
    @PostConstruct
    public void init(){
        List<NewsAppCache> all = appCacheRepository.findAll();
        for(NewsAppCache newsAppCache: all){
            APP_CACHE.put(newsAppCache.getKey(), newsAppCache.getValue());
        }
    }
}
