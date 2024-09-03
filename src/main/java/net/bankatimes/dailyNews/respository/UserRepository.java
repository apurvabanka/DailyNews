package net.bankatimes.dailyNews.respository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.bankatimes.dailyNews.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUsername(String username);

    User deleteByUsername(String username);
}
