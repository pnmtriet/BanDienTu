package com.poly.service.impl;

import com.poly.entity.Reviews;
import com.poly.service.ReviewService;
import com.poly.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;
    @Transactional(readOnly = true)
    @Override
    public List<Reviews> getReviewByProduct(int productId) {
        return reviewRepo.findByProductId(productId);
    }
}
