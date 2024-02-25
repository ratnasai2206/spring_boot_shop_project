package com.shop.projectshop.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.projectshop.entity.Users;
import com.shop.projectshop.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;
	
	
	// save the User into the database
	public Users saveUser(Users users) {
		return repository.save(users);
	}
	
	//get the User from the database
	public Users getUser(int user_Id) {
		System.err.println(repository);
		Optional<Users> optional=repository.findById(user_Id);
		if(optional.isPresent()) {
			Users user=optional.get();
			return user;
		}else {
			return null;
		}
	}
}
