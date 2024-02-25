package com.shop.projectshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shop.projectshop.dao.ProductDao;
import com.shop.projectshop.dao.ReviewDao;
import com.shop.projectshop.dao.UserDao;
import com.shop.projectshop.dto.ResponseStructure;
import com.shop.projectshop.entity.Orders;
import com.shop.projectshop.entity.Product;
import com.shop.projectshop.entity.Review;
import com.shop.projectshop.entity.Users;

@Service
public class ReviewServices {

	@Autowired
	private ReviewDao review_Dao;

	@Autowired
	private UserDao user_Dao;

	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Review>> saveReview(int product_Id, Review review, int user_Id) {
		Product product = productDao.getProductById(product_Id);
		Users users = user_Dao.getUser(user_Id);
		if (product != null) {
			List<Orders> orders = product.getOrders();
			int shopper_Id=0;
			for (Orders orders2 : orders) {
				if(orders2.getUser().getUser_Id()==user_Id) {
					shopper_Id=orders2.getUser().getUser_Id();
				}
			}
			Users user = product.getUser();
			if ((user != null)
					&& (users.getUser_Role().equalsIgnoreCase("merchant") && user.getUser_Id() == users.getUser_Id())|| (shopper_Id==user_Id)) {
				review.setProduct(product);
				Review reviews = review_Dao.saveReview(review);
				ResponseStructure<Review> responseStructure = new ResponseStructure<Review>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Review Created Successfully");
				responseStructure.setData(reviews);
				return new ResponseEntity<ResponseStructure<Review>>(responseStructure, HttpStatus.CREATED);
			} else {
				ResponseStructure<Review> responseStructure = new ResponseStructure<Review>();
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("You are not eligible to give reviews");
				responseStructure.setData(null);
				return new ResponseEntity<ResponseStructure<Review>>(responseStructure, HttpStatus.NOT_FOUND);
			}
		} else {
			ResponseStructure<Review> responseStructure = new ResponseStructure<Review>();
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Product Not Found to Review It");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Review>>(responseStructure, HttpStatus.NOT_FOUND);

		}
	}
	
	public ResponseEntity<ResponseStructure<Review>> deleteById(int review_Id,int user_Id){
		Users users=user_Dao.getUser(user_Id);
		if(users!=null && users.getUser_Role().equalsIgnoreCase("merchant")) {
			Review review=review_Dao.getReview(review_Id);
			if(review !=null && user_Id==review.getProduct().getUser().getUser_Id()) {
				String status=review_Dao.deleteReviewById(review_Id);
				if(status.equalsIgnoreCase("Success")) {
					ResponseStructure<Review> responseStructure=new ResponseStructure<Review>();
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setMessage("Record Deleted Successfully");
					responseStructure.setData(null);
					return new ResponseEntity<ResponseStructure<Review>>(responseStructure,HttpStatus.ACCEPTED);
				}
			}
		}
		ResponseStructure<Review> responseStructure=new ResponseStructure<Review>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Record Deleted Successfully");
		responseStructure.setData(null);
		return new ResponseEntity<ResponseStructure<Review>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	
	public ResponseEntity<ResponseStructure<List<Review>>> getAllReviews(int user_Id,int product_Id){
		Users users=user_Dao.getUser(user_Id);
		if(users!=null && users.getUser_Role().equalsIgnoreCase("merchant")) {
			List<Review> reviews=review_Dao.getAllReview(product_Id);
			if(reviews!=null) {
				ResponseStructure<List<Review>> responseStructure=new ResponseStructure<List<Review>>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("list of reviews");
				responseStructure.setData(reviews);
				return new ResponseEntity<ResponseStructure<List<Review>>>(responseStructure,HttpStatus.FOUND);
			}else {
				ResponseStructure<List<Review>> responseStructure=new ResponseStructure<List<Review>>();
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("Data not found");
				responseStructure.setData(null);
				return new ResponseEntity<ResponseStructure<List<Review>>>(responseStructure,HttpStatus.NOT_FOUND);
			}
		}else {
		ResponseStructure<List<Review>> responseStructure=new ResponseStructure<List<Review>>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Data not found");
		responseStructure.setData(null);
		return new ResponseEntity<ResponseStructure<List<Review>>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	

}
