package com.poly.service;
import com.poly.entity.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll(Integer soTrang, Integer soSanPham);
    List<Categories> findAllAdmin(Integer soTrang,Integer soSanPham);

    List<Categories> findCategoriesExist();

    Boolean existsById(Integer id);
    Categories save(Categories categoriesDataModel);

    Boolean delete(Integer id);
}
