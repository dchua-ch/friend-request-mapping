package com.example.friendsmapping.friendsmapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.example.friendsmapping.friendsmapping.helper.RequestStatus;
import com.example.friendsmapping.friendsmapping.model.FriendRequest;
import com.example.friendsmapping.friendsmapping.model.User;
import com.example.friendsmapping.friendsmapping.repo.FriendRequestRepo;
import com.example.friendsmapping.friendsmapping.repo.UserRepo;
import com.example.friendsmapping.friendsmapping.service.FriendRequestService;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FriendsmappingApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class FriendsmappingApplicationTests {
	

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private FriendRequestRepo fRepo;

	@Autowired
	private FriendRequestService fService;

	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
	@Order(2)
	void testDeleteAllFriendRequests() {
		fRepo.deleteAll();

		List<FriendRequest> requests = fRepo.findAll();

		assertEquals(requests.size(), 0);
	}

	@Test
	@Order(3)
	void testDeleteAllUsers() {
		uRepo.deleteAll();

		List<User> users = uRepo.findAll();

		assertEquals(users.size(), 0);

	}

	@Test
	@Order(4)
	void testInsertUser(){
	
		User jack = new User();
		jack.setUsername("jack22");
		jack.setPassword("jack22");


		User jill = new User();
		jill.setUsername("jill99");
		jill.setPassword("jill99");

		ArrayList<User> usersToAdd = new ArrayList<User>();
		usersToAdd.add(jack);
		usersToAdd.add(jill);


		uRepo.saveAll(usersToAdd);


		List<User> users = uRepo.findAll();

		assertEquals(users.size(), usersToAdd.size());



	}

	@Test
	@Order(5)
	void testFindByUsername() {
		String username = "jack22";
		User user = uRepo.findByUsername(username);

		if(user != null){
			assertEquals(user.getUsername(),username);
		}
	}

	@Test
	@Order(6)
	void testNewFriendRequest() {
		List<User> users = uRepo.findAll();

		FriendRequest request = new FriendRequest(users.get(0),users.get(1));

		assertEquals(request.getSender().getUsername(), users.get(0).getUsername());
		assertEquals(request.getRecipient().getUsername(), users.get(1).getUsername());
		assertEquals(request.getStatus(),RequestStatus.PENDING);;

		fRepo.saveAndFlush(request);

	}

	@Test
	@Order(7)
	void testFindPendingRequest(){
		List<FriendRequest> requests = fRepo.findPendingRequests();

		assertEquals(requests.size(),1);

		FriendRequest request = requests.get(0);

		assertEquals(request.getStatus(),RequestStatus.PENDING);
		assertEquals(request.getSender().getUsername(),"jack22");
		assertEquals(request.getRecipient().getUsername(),"jill99");

	}

	@Test 
	@Order(8)
	void testFindPendingRequestByUser(){
		String username = "jack22";
		User sender = uRepo.findByUsername(username);

		List<FriendRequest> requests = fRepo.findPendingRequestsBySender(sender);

		assertEquals(requests.size(), 1);

		FriendRequest request = requests.get(0);

		assertEquals(request.getSender().getUsername(), sender.getUsername());

	}

	@Test
	@Order(9)
	void testAcceptFriendRequest(){
		String username = "jack22";
		User sender = uRepo.findByUsername(username);

		List<FriendRequest> requests = fRepo.findPendingRequestsBySender(sender);

		assertEquals(requests.size(), 1);

		FriendRequest request = requests.get(0);

		fService.acceptRequest(request.getId());


		// Get updated sender and recipient objects from database

		sender = uRepo.findByUsername(request.getSender().getUsername());
		//User recipient = uRepo.findByUsername(request.getRecipient().getUsername());



		

       
	

	}



}
