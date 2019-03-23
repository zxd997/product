package com.zxd.product.repository;

import com.zxd.product.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findAllByProductStatus(int productStatus);
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
