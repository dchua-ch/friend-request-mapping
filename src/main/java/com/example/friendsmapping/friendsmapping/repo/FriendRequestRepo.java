package com.example.friendsmapping.friendsmapping.repo;

import java.util.List;

import com.example.friendsmapping.friendsmapping.model.FriendRequest;
import com.example.friendsmapping.friendsmapping.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface FriendRequestRepo extends JpaRepository<FriendRequest,Integer>{

    @Query ("SELECT f FROM FriendRequest f WHERE f.status = 'PENDING'")
    List<FriendRequest> findPendingRequests();

    @Query ("SELECT f FROM FriendRequest f WHERE f.status = 'PENDING' AND f.sender = :sender")
    List<FriendRequest> findPendingRequestsBySender(@Param("sender") User sender );
    
}
