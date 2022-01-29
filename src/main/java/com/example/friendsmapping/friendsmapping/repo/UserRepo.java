package com.example.friendsmapping.friendsmapping.repo;

import com.example.friendsmapping.friendsmapping.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends JpaRepository<User,Integer>{

    @Query("SELECT u FROM User u WHERE u.username = :username ")
    User findByUsername(@Param("username") String username);
    
}
