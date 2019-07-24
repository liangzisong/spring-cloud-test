package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (C), 2002-2019
 * FileName: DeptClientService
 * 让需求简单一点，心灵就会更轻松一点；
 * 让外表简单一点，本色就会更接近一点；
 * 让过程简单一点，内涵就会更丰富一点；
 * <p>
 * <p>
 * <p>
 * Description:
 *
 * @author 如果这段代码非常棒就是梁子松写的
 * 如果这代码挺差劲那么我也不知道是谁写的
 * @version 1.0.0
 * @create 19-7-21 下午1:23
 */
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @PostMapping(value = "/dept/add")
//    @HystrixCommand
    public boolean add(Dept dept);

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id);

    /*
    ！！！！！！！！！！！！！！！！！！
    ！！！！！！！！注！！！！！！！！！
    ！！！！！！！！意！！！！！！！！！
    ！！！！！！！！！！！！！！！！！！
    requestMapping 中的地址写服务提供者controller中的映射地址*/
    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list();

    @GetMapping(value = "/dept/discovery")
    public Object discovery();


}
