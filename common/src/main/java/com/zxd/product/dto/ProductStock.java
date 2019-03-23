package com.zxd.product.dto;

import lombok.Data;

@Data
public class ProductStock {
    private String productId;
    private Integer productQuantity;
    public ProductStock() {
    }
    public ProductStock(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
