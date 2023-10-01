package com.poly.service;

import com.poly.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll(Integer soTrang, Integer soSanPham);
    List<Brand> findAllAdmin(Integer soTrang,Integer soSanPham);

    List<Brand> findBrandExist();

    Boolean existsById(Integer id);
    Brand save(Brand brandDataModel);
    Boolean delete(Integer id);
}
