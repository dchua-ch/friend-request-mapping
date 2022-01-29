package com.example.friendsmapping.friendsmapping.service;

import com.example.friendsmapping.friendsmapping.helper.RequestStatus;
import com.example.friendsmapping.friendsmapping.model.FriendRequest;
import com.example.friendsmapping.friendsmapping.model.User;
import com.example.friendsmapping.friendsmapping.repo.FriendRequestRepo;
import com.example.friendsmapping.friendsmapping.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    FriendRequestRepo fRepo;

    @Autowired
    UserRepo uRepo;


    @Override
    public void acceptRequest(Integer id) {

        FriendRequest request = fRepo.findById(id).get();

        User sender = request.getSender();

        sender.getFriends().add(request.getRecipient());

        User recipient = request.getRecipient();
        
        recipient.getFriends().add(request.getSender());




        request.setStatus(RequestStatus.ACCEPTED);

        fRepo.saveAndFlush(request);
        uRepo.saveAndFlush(sender);


  


        
    }


    @Override
    public void findRequestBySender(User sender) {
        // TODO Auto-generated method stub


        
    }
    
}
