package com.shop.projectshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.projectshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	List<Product> findAllById(Iterable<Integer> ids); 
	
}
