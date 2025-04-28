package com.example.ErrandService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity

public class Errand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Skill> requiredSkills; // Errand can have multiple skills

    // Getters and setters for other fields...

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }





    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // No-args constructor
    public Errand() {}

    // Constructor with fields
    public Errand(Long id, String title, String description, String status, LocalDateTime createdAt, User user) {
        this.id = id;
        this.title = title;
        this.description = description;

        this.createdAt = createdAt;
        this.user = user;
    }

    // Getters and Setters (write manually since you don't want Lombok)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
