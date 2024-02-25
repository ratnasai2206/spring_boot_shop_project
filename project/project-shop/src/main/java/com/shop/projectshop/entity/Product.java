package com.shop.projectshop.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "product_id")
	@SequenceGenerator(name = "product_id",initialValue = 201,allocationSize = 1,sequenceName = "product_sequence")
	private int product_Id;
	private String product_Name;
	private String product_Type;
	private double product_Price;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Orders> orders=new ArrayList<Orders>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Review> reviews=new ArrayList<Review>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Users user;
}
