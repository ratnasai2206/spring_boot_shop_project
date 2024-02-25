package com.shop.projectshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shop.projectshop.entity.Product;
import com.shop.projectshop.repository.ProductRepository;
import com.shop.projectshop.repository.UserRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private UserRepository dao;

	// to save the product into the database
	public Product saveProduct( Product product) {
		
			return repository.save(product);
	}

	// to get the all product list to the customer
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	// to get product list w.r.to merchant id(user id)
//	public List<Product> getProductsByMerchantID(int user_Id){
//		return repository.findByUserId(user_Id);
//	}

	public Product getProductById(int id) {
		Optional<Product> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public String deleteProductById(int id) {
		Optional<Product> optional = repository.findById(id);
		if (optional.isPresent()) {
			Product product=optional.get();
			repository.delete(product);
			return "Success";
		} else {
			return "Failed";
		}
	}
	public List<Product> getProductsList(List<Integer> product_Ids){
		return repository.findAllById(product_Ids);
	}
}
