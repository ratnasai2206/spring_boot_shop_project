package com.shop.projectshop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "order_id")
	@SequenceGenerator(name = "order_id",initialValue = 101,allocationSize = 1,sequenceName = "order_sequence")
	private int order_Id;
	private String order_Description;
	private String order_Location;
	@CreationTimestamp
	private LocalDateTime create_Date;
	
	@ManyToOne
	@JoinColumn
	private Users user;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(joinColumns = @JoinColumn,inverseJoinColumns = @JoinColumn)
	private List<Product> products=new ArrayList<Product>();
}
