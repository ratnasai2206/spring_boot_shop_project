package com.shop.projectshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.projectshop.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	
}
