package com.poly.service.impl;

import com.poly.entity.Categories;
import com.poly.service.CategoriesService;
import com.poly.repository.CategoriesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepo categoriesRepo;
    @Override
    public List<Categories> findAll(Integer soTrang, Integer soSanPham) {
        Pageable pageable = PageRequest.of(soTrang-1, soSanPham);
        Page<Categories> pageCategories=categoriesRepo.findCategoriesExist(pageable);
        List<Categories> list=pageCategories.getContent();
        return list;
    }

    @Override
    public List<Categories> findAllAdmin(Integer soTrang,Integer soSanPham) {
        Pageable pageable = PageRequest.of(soTrang-1, soSanPham);
        Page<Categories> pageCategories=categoriesRepo.findAll(pageable);
        List<Categories> list=pageCategories.getContent();
        return list;
    }

    @Override
    public List<Categories> findCategoriesExist() {
        return categoriesRepo.findCategoriesExist();
    }

    @Override
    public Boolean existsById(Integer id) {
        return categoriesRepo.existsById(id);
    }

    @Override
    public Categories save(Categories categoriesDataModel) {
        Categories categoriesSaved=categoriesRepo.save(categoriesDataModel);
        return categoriesSaved;
    }

    @Override
    public Boolean delete(Integer id) {
        Categories categories=categoriesRepo.findById(id).get();
        if(categories!=null){
            categories.setDeleted(true);
            categoriesRepo.save(categories);
            return true;
        }else{
            return false;
        }
    }
}
