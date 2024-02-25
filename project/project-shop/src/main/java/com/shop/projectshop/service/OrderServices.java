package com.shop.projectshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shop.projectshop.dao.OrderDao;
import com.shop.projectshop.dao.ProductDao;
import com.shop.projectshop.dao.UserDao;
import com.shop.projectshop.dto.ResponseStructure;
import com.shop.projectshop.entity.Orders;
import com.shop.projectshop.entity.Product;

@Service
public class OrderServices {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	
	 public ResponseEntity<ResponseStructure<Orders>> createOrder(com.shop.projectshop.util.OrdersRequest orderRequest,int user_Id) {
	        Orders order=orderRequest.getOrders();
		 List<Product> products = productDao.getProductsList(orderRequest.getProductIds());
	        order.setProducts(products);
	        order.setUser(userDao.getUser(user_Id));
	        Orders orders=orderDao.createOrders(order);
	        if(orders!=null) {
	        	ResponseStructure<Orders> responseStructure = new ResponseStructure<Orders>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Order Created Successfully");
				responseStructure.setData(orders);
				return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);
	        }else {
	        	ResponseStructure<Orders> responseStructure = new ResponseStructure<Orders>();
				responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("Review Created Successfully");
				responseStructure.setData(orders);
				return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.BAD_GATEWAY);
	        }

	    }
	
}
