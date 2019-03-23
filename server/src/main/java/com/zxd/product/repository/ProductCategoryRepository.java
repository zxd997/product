package com.zxd.product.repository;

import com.zxd.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findAllByCategoryTypeIn(List<Integer> categoryTypeList);
}
