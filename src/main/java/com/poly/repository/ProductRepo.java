package com.poly.repository;
import com.poly.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.productName like %?1%")
    List<Product> findByName(String productName);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.productName like %?1%")
    Page<Product> findByName(Pageable pageable, String productName);
    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.price > ?1 and p.price <?2")
    List<Product> findByPrice(BigDecimal minPrice, BigDecimal maxPrice);
    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE")
    Page<Product> findProductExist(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE")
    List<Product> findProductExist();

    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.brandId=?1")
    List<Product> findByBrandId(Integer brandId);

    @Query("SELECT p FROM Product p WHERE p.brandId=?1")
    List<Product> findByBrandIdAdmin(Integer brandId);
    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.brandId=?1")
    Page<Product> findByBrandId(Pageable pageable,Integer brandId);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.categoryId=?1")
    List<Product> findByCategoryId(Integer categoryId);
    @Query("SELECT p FROM Product p WHERE p.categoryId=?1")
    List<Product> findByCategoryIdAdmin(Integer categoryId);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.categoryId=?1")
    Page<Product> findByCategoryId(Pageable pageable,Integer categoryId);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.brandId=?1 AND p.categoryId=?2")
    List<Product> findByBrandIdAndCategoryId(Integer brandId,Integer categoryId);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = FALSE AND p.brandId=?1 AND p.categoryId=?2 AND id!=?3")
    Page<Product> findByBrandIdAndCategoryId(Pageable pageable,Integer brandId,Integer categoryId,Integer oldProductId);

    @Query(value="select * from product p " +
            "left join ware_house wh " +
            "on p.warehouse_id = wh.id " +
            "where p.is_deleted = FALSE and wh.amount > 0 " +
            "order by p.number_of_sale asc",nativeQuery = true)
    List<Product> findProductSale();
}
