package com.poly.service.impl;

import com.poly.entity.Brand;
import com.poly.service.BrandService;
import com.poly.repository.BrandRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepo brandRepo;

    @Override
    public List<Brand> findAll(Integer soTrang, Integer soSanPham) {
        Pageable pageable = PageRequest.of(soTrang-1, soSanPham);
        Page<Brand> pageBrand=brandRepo.findBrandExist(pageable);
        List<Brand> list=pageBrand.getContent();
        return list;
    }

    @Override
    public List<Brand> findAllAdmin(Integer soTrang,Integer soSanPham) {
        Pageable pageable = PageRequest.of(soTrang-1, soSanPham);
        Page<Brand> pageBrand=brandRepo.findAll(pageable);
        List<Brand> list=pageBrand.getContent();
        return list;
    }

    @Override
    public List<Brand> findBrandExist() {
        return brandRepo.findBrandExist();
    }

    @Override
    public Boolean existsById(Integer id) {
        return brandRepo.existsById(id);
    }

    @Override
    public Brand save(Brand brand) {
        Brand brandSaved=brandRepo.save(brand);
        return brandSaved;
    }

    @Override
    public Boolean delete(Integer id) {
        Brand brand=brandRepo.findById(id).get();
        if(brand!=null){
            brand.setDeleted(true);
            brandRepo.save(brand);
            return true;
        }else{
            return false;
        }
    }
}
