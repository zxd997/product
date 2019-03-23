package com.zxd.product.service.impl;

import com.zxd.product.model.ProductCategory;
import com.zxd.product.repository.ProductCategoryRepository;
import com.zxd.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findAllByCategoryTypeIn(categoryTypeList);
    }
}
