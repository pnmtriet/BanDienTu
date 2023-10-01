package com.poly.repository;

import com.poly.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepo extends JpaRepository<Sale,Integer> {

    //Ngày gần nhất, giờ gần nhất
    @Query(value="SELECT * FROM sale where is_deleted = 0 order by sale_date_start asc, sale_time_start asc limit 1",nativeQuery = true)
    Sale findByRecentDay();

    @Query("SELECT s FROM Sale s WHERE s.saleName like %?1%")
    List<Sale> findBySaleName(String saleName);

    @Query("SELECT s FROM Sale s WHERE s.saleName like %?1%")
    Page<Sale> findBySaleName(Pageable pageable,String saleName);
}
