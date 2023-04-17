package ua.unblv.entity;

import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class Comment {
    private Long ID;
    private Post post;
    private String username;
    private Long userID;
    private String message;
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
