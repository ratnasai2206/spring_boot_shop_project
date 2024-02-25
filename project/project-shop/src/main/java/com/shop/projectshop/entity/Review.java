package com.shop.projectshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "review_id")
	@SequenceGenerator(name = "review_id",initialValue = 301,allocationSize = 1,sequenceName = "review_sequence")
	private int review_Id;
	private String review_Comments;
	private double review_Rating;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Product product;
}
