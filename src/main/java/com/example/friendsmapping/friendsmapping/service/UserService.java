package com.example.friendsmapping.friendsmapping.service;

import java.util.List;

import com.example.friendsmapping.friendsmapping.model.User;

import org.springframework.stereotype.Component;

@Component
public interface UserService {

    List<User> findFriendsOf(User user);

    
    
}
