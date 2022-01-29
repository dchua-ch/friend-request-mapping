package com.example.friendsmapping.friendsmapping.service;

import java.util.List;

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

        request.setStatus(RequestStatus.ACCEPTED);

        fRepo.saveAndFlush(request);
        
    }


    @Override
    public boolean sendRequest(User sender, User recipient) {
        if(sender.equals(recipient)){
            return false;
        }
        List<FriendRequest> existingRequests = fRepo.findExistingRequests(sender, recipient);
        if(existingRequests.size() > 0) {
            return false;
        }

        fRepo.saveAndFlush(new FriendRequest(sender,recipient));
        return true;
        
    }


    @Override
    public void rejectRequest(FriendRequest request) {
        fRepo.delete(request);
        
    }
    
  
}
