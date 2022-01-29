package com.example.friendsmapping.friendsmapping.service;



import com.example.friendsmapping.friendsmapping.model.User;

import org.springframework.stereotype.Component;
@Component
public interface FriendRequestService {

    public void acceptRequest(Integer id);

    public void findRequestBySender(User sender);
}
