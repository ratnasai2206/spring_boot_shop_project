package com.shop.projectshop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.projectshop.entity.Orders;
import com.shop.projectshop.repository.OrderRepository;
import com.shop.projectshop.repository.ProductRepository;


@Repository
public class OrderDao {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private ProductRepository productRepository;
   


//	 public Orders createOrder(OrdersRequest orderRequest) {
//	        Orders order = new Orders();
//	        order.setOrder_Description(orderRequest.getOrder_Description());
//	        order.setOrder_Location(orderRequest.getOrder_Location());
//
//	        List<Product> products = productRepository.findAllById(orderRequest.getProductIds());
//	        order.setProducts(products);
//
//	        return repository.save(order);
//	    }
	
	public Orders createOrders(Orders orders) {
		return repository.save(orders);
	}

    public Orders getOrderById(int orderId) {
        return repository.findById(orderId).orElse(null);
    }

    public List<Orders> getAllOrders() {
        return repository.findAll();
    }

 
    
    
  
}
