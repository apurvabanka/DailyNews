package net.bankatimes.dailyNews.entity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;


@Document(collection = "news_entries")
@Data
@NoArgsConstructor
public class NewsEntry {
    
    @Id
    private ObjectId id;
    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;
    
}
