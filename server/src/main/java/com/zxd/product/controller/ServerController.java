package com.zxd.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    /**
     * 作为服务端给订单用的演示
     * @return
     */
    @GetMapping("/msg")
    public String msg(){
        return "this is a com.zxd.product msg1";
    }
}
