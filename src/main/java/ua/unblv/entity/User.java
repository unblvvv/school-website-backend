package ua.unblv.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.Data;
import ua.unblv.entity.enums.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private Long ID;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String bio;
    private String password;
    private LocalDateTime createdDate;
    private Set<Role> role = new HashSet<>();
    private List<Post> posts = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
