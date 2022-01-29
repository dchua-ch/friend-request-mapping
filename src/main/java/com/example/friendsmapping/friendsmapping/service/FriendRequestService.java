package com.example.friendsmapping.friendsmapping.service;



import com.example.friendsmapping.friendsmapping.model.FriendRequest;
import com.example.friendsmapping.friendsmapping.model.User;

import org.springframework.stereotype.Component;
@Component
public interface FriendRequestService {

    public boolean sendRequest(User sender, User recipient);

    public void acceptRequest(Integer id);

    public void rejectRequest(FriendRequest request);

  
}
