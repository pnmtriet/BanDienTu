package com.poly.service.impl;

import com.poly.entity.ProductImage;
import com.poly.repository.ProductImageRepo;
import com.poly.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageRepo productImageRepo;


    @Override
    public List<ProductImage> findByProductId(Integer productId) {
        return productImageRepo.findByProductId(productId);
    }

    @Override
    public Boolean save(Integer productId,String[] images) {
        try{
            for(int i=0;i<images.length;i++){
                ProductImage productImage=new ProductImage();
                productImage.setProductImageName(images[i]);
                productImage.setProductId(productId);
                productImageRepo.save(productImage);
            }
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
