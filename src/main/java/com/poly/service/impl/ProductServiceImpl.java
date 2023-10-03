package com.poly.service.impl;

import com.poly.entity.Product;
import com.poly.entity.ProductImage;
import com.poly.service.ProductImageService;
import com.poly.repository.ProductRepo;
import com.poly.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductImageService productImageService;
    private String[] getImages(Product product){
        List<ProductImage> list=productImageService.findByProductId(product.getId());
        String[] images=new String[list.size()];
        if(!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                images[i]=list.get(i).getProductImageName();
            }
        }
        return images;
    }


    @Override
    public List<Product> findAll(Integer soTrang,Integer soSanPham) {
        Pageable pageable = PageRequest.of(soTrang-1, soSanPham);
        Page<Product> pageProduct=productRepo.findAll(pageable);
        List<Product> list=pageProduct.getContent();
        return list;
    }

    @Override
    public List<Product> findProductExist(Integer soTrang,Integer soSanPham) {
        Pageable pageable = PageRequest.of(soTrang-1, soSanPham);
        Page<Product> pageProduct=productRepo.findProductExist(pageable);
        List<Product> list=pageProduct.getContent();
        return list;
    }

    @Override
    public Page<Product> findProductExist(Pageable pageable) {
        return productRepo.findProductExist(pageable);
    }

    @Override
    public List<Product> findProductExist2() {
        return productRepo.findProductExist();
    }

    @Override
    public Product findById(Integer id) {
        Product result=productRepo.findById(id).get();
        return result;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> list=productRepo.findByName(name);
        return list;
    }

    @Override
    public Page<Product> findByName(Pageable pageable, String productName) {
        return productRepo.findByName(pageable,productName);
    }

    @Override
    public List<Product> findByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> list=productRepo.findByPrice(minPrice, maxPrice);
        return list;
    }

    @Override
    public boolean existsById(Integer id) {
        return productRepo.existsById(id);
    }

    @Override
    public Product save(Product product) {
        Product productSaved=productRepo.save(product);
        return productSaved;
    }

}
