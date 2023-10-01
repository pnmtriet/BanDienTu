package com.poly.service;
import com.poly.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> findAll(Integer soTrang, Integer soSanPham);
    List<Product> findProductExist(Integer soTrang,Integer soSanPham);

    Page<Product> findProductExist(Pageable pageable);

    List<Product> findProductExist2();
    Product findById(Integer id);
    List<Product> findByName(String name);

    Page<Product> findByName(Pageable pageable, String productName);
    List<Product> findByPrice(BigDecimal minPrice, BigDecimal maxPrice);
    boolean existsById(Integer id);
    Product save(Product productDataModel);
}
