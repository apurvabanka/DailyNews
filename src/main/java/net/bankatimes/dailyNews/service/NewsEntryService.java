package net.bankatimes.dailyNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import net.bankatimes.dailyNews.entity.NewsEntry;
import net.bankatimes.dailyNews.entity.User;
import net.bankatimes.dailyNews.respository.NewsEntryRepository;
import net.bankatimes.dailyNews.respository.UserRepository;

@Component
public class NewsEntryService {
    
    @Autowired
    private NewsEntryRepository newsEntryRepository;
    @Autowired
    private UserRepository userRepository;

    public void saveNewsEntry(NewsEntry newsEntry){
        newsEntryRepository.save(newsEntry);
    }

    @Transactional// Added to achieve to atomocity
    public void saveNewsEntry(NewsEntry newsEntry, String username){
        User getUser = userRepository.findByUsername(username);
        getUser.getNewsEntries().add(newsEntry);
        newsEntryRepository.save(newsEntry);
        userRepository.save(getUser);
    }
    

    public List<NewsEntry> getAll(){
        return newsEntryRepository.findAll();
    }

    public void deleteNewsById(ObjectId id, String username){
        User user = userRepository.findByUsername(username);
        user.getNewsEntries().removeIf(x-> x.getId().equals(id));
        userRepository.save(user);
        newsEntryRepository.deleteById(id);
    }

    public Optional<NewsEntry> findById(ObjectId id){
        return newsEntryRepository.findById(id);
    }

}
