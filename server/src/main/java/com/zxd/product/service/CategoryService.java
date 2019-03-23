package com.zxd.product.service;

import com.zxd.product.model.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList);
}
