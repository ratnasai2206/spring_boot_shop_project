package com.shop.projectshop.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewsRequest<T> {

	 private List<T> data;
}
