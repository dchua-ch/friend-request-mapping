package com.example.friendsmapping.friendsmapping.service;

import java.util.ArrayList;
import java.util.List;

import com.example.friendsmapping.friendsmapping.model.FriendRequest;
import com.example.friendsmapping.friendsmapping.model.User;
import com.example.friendsmapping.friendsmapping.repo.FriendRequestRepo;
import com.example.friendsmapping.friendsmapping.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired 
    UserRepo uRepo;

    @Autowired
    FriendRequestRepo fRepo;

    @Autowired
    FriendRequestService fService;

    @Override
    public ArrayList<User> findFriendsOf(User user) {

        ArrayList<User> friends = new ArrayList<User>();

        List<FriendRequest> acceptedRequests = fRepo.findAcceptedRequestsByUser(user);

        for(FriendRequest request : acceptedRequests){
            if(request.getSender().equals(user)){
                friends.add(request.getRecipient());
            }
            else {
                friends.add(request.getSender());
            }
        }



        return friends;
    }
    
}
