package com.poly.service;

import com.poly.entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> findByProductId(Integer productId);
    Boolean save(Integer productId,String[] images);
}
