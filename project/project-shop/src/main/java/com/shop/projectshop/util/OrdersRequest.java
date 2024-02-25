package com.shop.projectshop.util;

import java.util.List;

import com.shop.projectshop.entity.Orders;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrdersRequest {

	 	private Orders orders;
	    private List<Integer> productIds; 
}
