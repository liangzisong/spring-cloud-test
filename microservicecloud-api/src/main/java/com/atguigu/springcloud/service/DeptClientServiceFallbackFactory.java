package com.atguigu.springcloud.service;//
//
//
//

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                       //
////////////////////////////////////////////////////////////////////
//
//
//
//
//让需求简单一点，心灵就会更轻松一点；
//让外表简单一点，本色就会更接近一点；
//让过程简单一点，内涵就会更丰富一点；
//
//
//


import com.atguigu.springcloud.entities.Dept;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.netflix.loadbalancer.RandomRule;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright (C), 2002-2019
 * FileName: DeptClientServiceFallbackFactory
 * <p>
 * Description: 服务调用失败走的方法工厂
 *
 * @author 如果这段代码非常棒就是梁子松写的
 * 如果这代码挺差劲那么我也不知道是谁写的
 * @version 1.0.0
 * @create 19-7-21 下午5:28
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {


    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                System.out.println("throwable=");
                if(throwable instanceof RuntimeException){
                    throw (RuntimeException)throwable;
                }
                /*超时*/
                if(throwable instanceof HystrixTimeoutException){

                }
                /*运行异常，可能是服务关闭*/
                if(throwable instanceof HystrixRuntimeException){

                }
                throwable.printStackTrace();
                Dept dept = new Dept();
                {
                    dept.setDeptno(id);
                    dept.setDb_source("no this database in mysql");
                    dept.setDname("server showdown baibai~");
                }
                return dept;
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public Object discovery() {
                return null;
            }
        };
    }
}