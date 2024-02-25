package com.shop.projectshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.projectshop.ProjectShopApplication;
import com.shop.projectshop.dto.ResponseStructure;
import com.shop.projectshop.entity.Orders;
import com.shop.projectshop.entity.Product;
import com.shop.projectshop.entity.Review;
import com.shop.projectshop.entity.Users;
import com.shop.projectshop.service.OrderServices;
import com.shop.projectshop.service.ProductServices;
import com.shop.projectshop.service.ReviewServices;
import com.shop.projectshop.service.UserServices;
import com.shop.projectshop.util.OrdersRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private UserServices user_Services;
	@Autowired
	private ProductServices product_Services;
	@Autowired
	private ReviewServices review_Services;
	@Autowired
	private OrderServices order_Services;
	
	@Operation(description = "To Create Users info",summary = "User will be saved in the database")
	@ApiResponses(value = {@ApiResponse(description = "User Created",responseCode = "201"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@PostMapping(value = "/user",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Users>> saveEmployee(@RequestBody Users user) {
		return user_Services.saveUser(user);
	}
	
	
	@Operation(description = "To get Users info",summary = "User will be found")
	@ApiResponses(value = {@ApiResponse(description = "User Found",responseCode = "200"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@GetMapping(value = "/user/{user_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Users>> getUsers(@PathVariable int user_Id){
		return user_Services.getUser(user_Id);
	}
	
	
	@Operation(description = "To save the product",summary = "Product will be saved")
	@ApiResponses(value = {@ApiResponse(description = "Product Created",responseCode = "201"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@PostMapping(value = "/product/user_id/{user_id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@PathVariable int user_id, @RequestBody Product product){
		return product_Services.saveProduct(user_id, product);
	}
	@Operation(description = "To update the product",summary = "product will be updated")
	@ApiResponses(value = {@ApiResponse(description = "Product Updated",responseCode = "200"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@PutMapping(value = "/product/user_Id/{user_Id}/product_Id/{product_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int user_Id,@PathVariable int product_Id, @RequestBody Product product){
		return product_Services.updateProduct(user_Id, product_Id, product);
	}
	@Operation(description = "To delete the product",summary = "product will be deleted")
	@ApiResponses(value = {@ApiResponse(description = "Deleted Product",responseCode = "202"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@DeleteMapping(value = "/product/user_Id/{user_Id}/product_Id/{product_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int user_Id,@PathVariable int product_Id) {
		return product_Services.deleteProductById(product_Id, user_Id);
	}
	@Operation(description = "To get product info",summary = "product details will be found")
	@ApiResponses(value = {@ApiResponse(description = "Product Found",responseCode = "200"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@GetMapping(value = "/product/product_Id/{product_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Product>> getProduct(@PathVariable int product_Id) {
		return product_Services.getProdut(product_Id);
	}
	@Operation(description = "To save the review info",summary = "review will be saved")
	@ApiResponses(value = {@ApiResponse(description = "review Created",responseCode = "201"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@PostMapping(value = "/review/product_Id/{product_Id}/user_Id/{user_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Review>> saveReview(@PathVariable int product_Id,@RequestBody Review review,@PathVariable int user_Id) {
		return review_Services.saveReview(product_Id, review, user_Id);
	}
	@Operation(description = "To delete the review info",summary = "review will be deleted")
	@ApiResponses(value = {@ApiResponse(description = "Deleted review",responseCode = "202"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@DeleteMapping(value = "/review/user_Id/{user_Id}/review_Id/{review_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Review>> deleteReview(@PathVariable int user_Id,@PathVariable int review_Id){
		return review_Services.deleteById(review_Id, user_Id);
	}
	@Operation(description = "To get all review info",summary = "reviews will be displayed")
	@ApiResponses(value = {@ApiResponse(description = "Reviews Found",responseCode = "200"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@GetMapping(value = "/review/user_Id/{user_Id}/product_Id/{product_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<List<Review>>> getAllReviews(@PathVariable int user_Id,@PathVariable int product_Id) {
		return review_Services.getAllReviews(user_Id, product_Id);
	}
	@Operation(description = "To save the orders info",summary = "orders will be saved")
	@ApiResponses(value = {@ApiResponse(description = "Order Created",responseCode = "201"),
			@ApiResponse(content = @Content(),responseCode = "400" )})
	@PostMapping(value = "/order/user_Id/{user_Id}",consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseStructure<Orders>> saveOrders(@RequestBody OrdersRequest ordersRequest,@PathVariable int user_Id ){
		return order_Services.createOrder(ordersRequest,user_Id);
	}
	
	@GetMapping("/exit")
	public void shutdown() {
		SpringApplication.exit(ProjectShopApplication.applicationContext);
	}
}
