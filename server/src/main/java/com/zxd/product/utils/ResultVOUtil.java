package com.zxd.product.utils;

import com.zxd.product.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(o);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }
}
