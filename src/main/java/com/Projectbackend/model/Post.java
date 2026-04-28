package com.Projectbackend.model;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String citizenName;

    private String role; // ✅ ADD THIS

    public Post() {
    }

    public Post(Long id, String title, String description, String citizenName, String role) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.citizenName = citizenName;
        this.role = role;
    }

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

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    // ✅ NEW GETTER & SETTER

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}