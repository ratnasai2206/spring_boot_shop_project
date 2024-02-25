package com.shop.projectshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.projectshop.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Query("SELECT r FROM Review r WHERE r.product.id = :productId")
	 List<Review> findAllByProductId(int productId);
}
