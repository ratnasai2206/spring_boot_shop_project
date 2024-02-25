package com.shop.projectshop.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_id")
	@SequenceGenerator(name = "user_id",initialValue = 1,allocationSize = 1,sequenceName = "user_sequence")
	private int user_Id;
	private String user_Name;
	private String user_Email;
	private String user_Password;
	private String user_Role;
	@JsonIgnore
	@OneToMany(mappedBy ="user",cascade = CascadeType.ALL )
	private List<Orders>orders=new ArrayList<Orders>();
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Product>products=new ArrayList<Product>();

}
