package com.example.friendsmapping.friendsmapping.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> friends;

    // This is the inverse side
    @OneToMany(mappedBy = "recipient")
    private List<FriendRequest> receivedRequests;


    // This is the inverse side
    @OneToMany(mappedBy="sender")
    private List<FriendRequest> sentRequests;



    
}
