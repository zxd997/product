package com.zxd.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.tools.json.JSONUtil;
import com.zxd.product.dto.ProductStock;
import com.zxd.product.enums.ProductStatusEnum;
import com.zxd.product.enums.ResultEnum;
import com.zxd.product.exception.ProductException;
import com.zxd.product.model.ProductInfo;
import com.zxd.product.repository.ProductInfoRepository;
import com.zxd.product.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Override
    public List<ProductInfo> findAllUpProduct() {
        return productInfoRepository.findAllByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findProductForOrder(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decrease(List<ProductStock> productStockList) {
        List<ProductInfo> productInfoList = decrease1(productStockList);
        //发送mq消息
        amqpTemplate.convertAndSend("productInfo", JSONObject.toJSONString(productInfoList));
    }
    @Transactional
    public List<ProductInfo> decrease1(List<ProductStock> productStockList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (ProductStock productStock : productStockList){
            Optional<ProductInfo> productInfo = productInfoRepository.findById(productStock.getProductId());
            //判断商品是否存在
            if (!productInfo.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo1 = productInfo.get();
            int result = productInfo1.getProductStock() - productStock.getProductQuantity();
            //判断商品是否足够
            if (result<0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo1.setProductStock(result);
            productInfoRepository.save(productInfo1);
            productInfoList.add(productInfo1);
        }
        return productInfoList;
    }

}

