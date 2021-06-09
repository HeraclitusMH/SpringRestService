package com.heraclitus.springstudyproject.restfulwebservice.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Integer getId() { return id; }

    public void setId(Integer id) { id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public void setUser(User user) { this.user = user; }
}
