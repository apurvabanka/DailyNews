package net.bankatimes.dailyNews.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import lombok.Builder;
import lombok.Data;

@Document(collection="users")
@Data
@Builder
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    private boolean sentimantAnalysis;
    private String email;
    @DBRef
    private List<NewsEntry> newsEntries = new ArrayList<>();
    private List<String> roles;
    
}
