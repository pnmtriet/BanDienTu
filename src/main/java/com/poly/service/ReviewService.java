package com.poly.service;

import com.poly.entity.Reviews;

import java.util.List;

public interface ReviewService {
    List<Reviews> getReviewByProduct(int productId);
}
