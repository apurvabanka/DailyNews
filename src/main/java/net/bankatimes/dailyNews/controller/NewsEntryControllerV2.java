package net.bankatimes.dailyNews.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bankatimes.dailyNews.entity.NewsEntry;
import net.bankatimes.dailyNews.entity.User;
import net.bankatimes.dailyNews.service.NewsEntryService;
import net.bankatimes.dailyNews.service.UserService;

@RestController
@RequestMapping("/news")
public class NewsEntryControllerV2 {

    @Autowired
    private NewsEntryService newsEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<NewsEntry> getAll(){
        return newsEntryService.getAll();
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getNewsByUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user.getNewsEntries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsEntry> createEntry(@RequestBody NewsEntry newsEntry){

        try {
            newsEntry.setDate(LocalDateTime.now());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            newsEntryService.saveNewsEntry(newsEntry, username);
            return new ResponseEntity<>(newsEntry, HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<NewsEntry> getNewsById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<NewsEntry> collect = user.getNewsEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<NewsEntry> newsEntry = newsEntryService.findById(myId);
            if (newsEntry.isPresent()) {
                return new ResponseEntity<>(newsEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}/{myId}")
    public ResponseEntity<?> deleteNewsById(@PathVariable ObjectId myId, @PathVariable String username){
        newsEntryService.deleteNewsById(myId, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/id/{username}/{myId}")
    public NewsEntry updateNews(
        @PathVariable ObjectId myId,
        @PathVariable String username,
        @RequestBody NewsEntry myEntry
        ){
        NewsEntry old = newsEntryService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
        }
        newsEntryService.saveNewsEntry(old);
        return old;
    }
}
