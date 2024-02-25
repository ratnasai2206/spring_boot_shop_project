package com.shop.projectshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shop.projectshop.dao.ProductDao;
import com.shop.projectshop.dao.UserDao;
import com.shop.projectshop.dto.ResponseStructure;
import com.shop.projectshop.entity.Product;
import com.shop.projectshop.entity.Users;

@Service
public class ProductServices {

	@Autowired
	private ProductDao dao;
	@Autowired
	private UserDao user_Dao;
	
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(int user_Id ,Product product) {
		Users users=user_Dao.getUser(user_Id);
		if(users!=null && users.getUser_Role().equalsIgnoreCase("merchant")) {
			product.setUser(users);
			Product recivedproduct=dao.saveProduct(product);
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(recivedproduct);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.CREATED);
		}else {
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("failed to save");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int user_Id,int product_Id,Product product){
		
		Users users=user_Dao.getUser(user_Id);
		if(users!=null && users.getUser_Role().equalsIgnoreCase("merchant")) {
			Product oldProduct=dao.getProductById(product_Id);
			if(oldProduct!=null && user_Id == oldProduct.getUser().getUser_Id()) {
					if(product.getProduct_Name()!=null) {
						oldProduct.setProduct_Name(product.getProduct_Name());
					}
					if(product.getProduct_Price()!=0.0) {
						oldProduct.setProduct_Price(product.getProduct_Price());
					}
					if(product.getProduct_Type()!=null) {
						oldProduct.setProduct_Type(product.getProduct_Type());
					}
				}
			Product recivedproduct=dao.saveProduct(oldProduct);
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Successfully updated");
			responseStructure.setData(recivedproduct);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
		}else {
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("failed to update the product");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(int product_Id,int user_Id){
		Users users=user_Dao.getUser(user_Id);
		if(users!=null && users.getUser_Role().equalsIgnoreCase("merchant")) {
			Product product=dao.getProductById(product_Id);
			if(product !=null && user_Id==product.getUser().getUser_Id()) {
				String status=dao.deleteProductById(product_Id);
				if(status.equalsIgnoreCase("Success")) {
					ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setMessage("Record Deleted Successfully");
					responseStructure.setData(null);
					return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.ACCEPTED);
				}
			}
		}
		ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Record Deleted Successfully");
		responseStructure.setData(null);
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.NO_CONTENT);

	}
	
	public ResponseEntity<ResponseStructure<Product>> getProdut(int product_Id) {
		Product product=dao.getProductById(product_Id);
		if(product!=null) {
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Record Deleted Successfully");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.ACCEPTED);
		}else {
			ResponseStructure<Product> responseStructure=new ResponseStructure<Product>();
			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Record Deleted Successfully");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.NO_CONTENT);
		}
		
	}
}
