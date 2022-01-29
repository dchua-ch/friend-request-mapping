package com.example.friendsmapping.friendsmapping.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    // This is the inverse side
    @OneToMany(mappedBy = "recipient")
    private List<FriendRequest> receivedRequests;


    // This is the inverse side
    @OneToMany(mappedBy="sender")
    private List<FriendRequest> sentRequests;


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        
        User user = (User) o;
 
        return this.getUsername().equals(user.getUsername());
        
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, receivedRequests, sentRequests);
    }




    
}
