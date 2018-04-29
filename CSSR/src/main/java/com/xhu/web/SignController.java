package com.xhu.web;

import com.xhu.dto.ResultBean;
import com.xhu.entity.Sign;
import com.xhu.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gp on 2018/4/9.
 */
@RestController
@RequestMapping("/api")
public class SignController {
    @Autowired
    private SignService signService;

    @RequestMapping(value = "/sign/get-sign",method = RequestMethod.POST)
    private ResultBean getSign(@RequestBody Sign sign){
        return new ResultBean("200",signService.getSign(sign),"签约信息");
    }
}
