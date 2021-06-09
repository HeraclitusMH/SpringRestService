package com.heraclitus.springstudyproject.restfulwebservice.repository;

import com.heraclitus.springstudyproject.restfulwebservice.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
