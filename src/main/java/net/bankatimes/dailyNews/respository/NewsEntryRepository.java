package net.bankatimes.dailyNews.respository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.bankatimes.dailyNews.entity.NewsEntry;

public interface NewsEntryRepository extends MongoRepository<NewsEntry, ObjectId>{
    
}
