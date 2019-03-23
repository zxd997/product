package com.zxd.product.client;

import com.zxd.product.dto.ProductStock;
import com.zxd.product.model.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@FeignClient(name = "product",fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {
    @GetMapping("/msg")
    String getMsg();
    @PostMapping("/product/listForOrder")
    List<ProductInfo> getListforOrder(List<String> productIdList);
    @PostMapping("/product/decreaseStock")
    void decreaseStock(List<ProductStock> productStockList);

    @Component
    //默认也是一秒
    static class ProductClientFallback implements ProductClient{

        @Override
        public String getMsg() {
            return null;
        }

        @Override
        public List<ProductInfo> getListforOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<ProductStock> productStockList) {

        }
    }
}
