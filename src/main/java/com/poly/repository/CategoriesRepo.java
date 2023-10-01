package com.poly.repository;
import com.poly.entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories,Integer> {
    @Query("SELECT c FROM Categories c WHERE c.isDeleted = FALSE")
    Page<Categories> findCategoriesExist(Pageable pageable);
    @Query("SELECT c FROM Categories c WHERE c.isDeleted = FALSE")
    List<Categories> findCategoriesExist();

    @Query("SELECT c FROM Categories c WHERE c.categoryName like %?1%")
    List<Categories> findByCategoryName(String categoryName);

    @Query("SELECT c FROM Categories c WHERE c.categoryName like %?1%")
    Page<Categories> findByCategoryName(Pageable pageable, String categoryName);
}
