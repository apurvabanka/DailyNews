package net.bankatimes.dailyNews.respository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.bankatimes.dailyNews.entity.NewsAppCache;


public interface AppCacheRepository extends MongoRepository<NewsAppCache, ObjectId>{
    
}
