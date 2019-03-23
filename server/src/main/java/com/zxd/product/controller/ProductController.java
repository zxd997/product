package com.zxd.product.controller;

import com.zxd.product.dto.ProductStock;
import com.zxd.product.model.ProductCategory;
import com.zxd.product.model.ProductInfo;
import com.zxd.product.service.CategoryService;
import com.zxd.product.service.ProductService;
import com.zxd.product.utils.ResultVOUtil;
import com.zxd.product.vo.ProductInfoVO;
import com.zxd.product.vo.ProductVO;
import com.zxd.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 1.查询所有在架商品
     * 2.获取类目的type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    //@CrossOrigin(allowCredentials = "true",) 跨域  允许cookie跨域  这是单个的实现，整体的 去zuul配置
    public ResultVO<ProductVO> list(HttpServletRequest request){
        //1.查询所有在架商品
        List<ProductInfo> upProductList = productService.findAllUpProduct();
        //2.获取查得商品的type列表
        List<Integer> categoryTypeList = upProductList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3.查询类目,根据类目的序号 查的全部信息
        List<ProductCategory> categoryList = categoryService.findAllByCategoryTypeIn(categoryTypeList);
        //4.构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : upProductList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
    /**
     * 查询商品信息 (给订单服务用)
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> getListforOrder(@RequestBody List<String> productIdList){
//        try {
//            Thread.sleep(2000);//一般默认之请求1.03s
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return productService.findProductForOrder(productIdList);
    }
    /**
     * 扣库存
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<ProductStock> productStockList){
        productService.decrease(productStockList);
    }
    /**
     * 测试zuul的路由排除
     */
    @GetMapping("/detest")
    public String funtion(){
        return "被排除了";
    }
}
