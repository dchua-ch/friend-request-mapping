package com.example.friendsmapping.friendsmapping.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.friendsmapping.friendsmapping.helper.RequestStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class FriendRequest {

    public FriendRequest(User sender, User recipient){
        this.sender = sender;
        this.recipient = recipient;
        this.status = RequestStatus.PENDING;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    

    
}
