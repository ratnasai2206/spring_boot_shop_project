package com.shop.projectshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shop.projectshop.dao.UserDao;
import com.shop.projectshop.dto.ResponseStructure;
import com.shop.projectshop.entity.Users;
import com.shop.projectshop.exception.UserIdDoesNotPresentException;

@Service
public class UserServices {
	
	@Autowired
	private  UserDao dao;
	
	public ResponseEntity<ResponseStructure<Users>> saveUser(Users users) {
		Users recivedUsers=dao.saveUser(users);
		ResponseStructure<Users> responseStructure=new ResponseStructure<Users>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recivedUsers);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Users>> getUser(int id) {
		Users user=dao.getUser(id);
		if(user!=null) {
			ResponseStructure<Users> responseStructure=new ResponseStructure<Users>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure,HttpStatus.OK);
		}else {
			throw new UserIdDoesNotPresentException("Id : "+id+" , not present in Database");
		}
		
	}

}
