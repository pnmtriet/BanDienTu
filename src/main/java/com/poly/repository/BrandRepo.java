package com.poly.repository;

import com.poly.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand,Integer> {
    @Query("SELECT b FROM Brand b WHERE b.isDeleted = FALSE")
    Page<Brand> findBrandExist(Pageable pageable);

    @Query("SELECT b FROM Brand b WHERE b.isDeleted = FALSE")
    List<Brand> findBrandExist();

    @Query("SELECT c FROM Brand c WHERE c.brandName like %?1%")
    List<Brand> findByBrandName(String brandName);

    @Query("SELECT c FROM Brand c WHERE c.brandName like %?1%")
    Page<Brand> findByBrandName(Pageable pageable, String brandName);
}
