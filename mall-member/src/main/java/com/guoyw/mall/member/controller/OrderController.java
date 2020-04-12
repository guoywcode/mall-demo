package com.guoyw.mall.member.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class OrderController extends BaseController {

    @GetMapping("/order")
    public String order(){
      log.info("请求成功！！");

        return "请求成功";
    }
}
