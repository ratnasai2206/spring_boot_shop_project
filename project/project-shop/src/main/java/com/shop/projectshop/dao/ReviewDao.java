package com.shop.projectshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.projectshop.entity.Review;
import com.shop.projectshop.repository.ReviewRepository;

@Repository
public class ReviewDao {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private ProductDao productDao;
	
//	public Review saveReview(int product_Id,Review review)  {
//		Product product = productDao.getProductById(product_Id);
//		if(product != null) {
//			List<Orders> orders = product.getOrders();
//			if(orders.isEmpty()) {
//				return null;
//			} else {	
//				review.setProduct(product);
//				return repository.save(review);
//			}
//		} else {
//			return null;
//		}
//	}
	
	// it is used to save the reviews into the data base
	public Review saveReview(Review review) {
		return repository.save(review);
		
	}
	
	public Review getReview(int id) {
		Optional<Review> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public List<Review> getAllReview(int product_Id) {
		return repository.findAllByProductId(product_Id);
	}
	
	public String deleteReviewById(int id) {
		Optional<Review> optional = repository.findById(id);
		if (optional.isPresent()) {
			Review review=optional.get();
			repository.delete(review);
			return "Success";
		} else {
			return "Failed";
		}
	}
	
	
}
