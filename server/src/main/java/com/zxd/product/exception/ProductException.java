package com.zxd.product.exception;

import com.zxd.product.enums.ResultEnum;

public class ProductException extends RuntimeException {
    private Integer code;
    private String msg;

    public ProductException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ProductException(ResultEnum resultEnum) {
        this.code = code;
        this.msg = msg;
    }
}
