package com.zxd.product.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    PRODUCT_NOT_EXIST(1,"产品不存在"),
    PRODUCT_STOCK_ERROR(2,"商品库存错误"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
