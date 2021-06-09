package com.heraclitus.springstudyproject.restfulwebservice.repository;

import com.heraclitus.springstudyproject.restfulwebservice.beans.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
