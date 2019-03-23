package com.zxd.product.service;

import com.zxd.product.dto.ProductStock;
import com.zxd.product.model.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品
     */
    List<ProductInfo> findAllUpProduct();
    /**
     * 查询ById
     */
    List<ProductInfo> findProductForOrder(List<String> productIdList);

    /**
     * 扣库存
     * @param productStockList
     */
    void decrease(List<ProductStock> productStockList);
}
