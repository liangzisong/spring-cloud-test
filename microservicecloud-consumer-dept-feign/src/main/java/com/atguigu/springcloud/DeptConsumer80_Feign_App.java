package com.atguigu.springcloud;

import com.atguigu.myRule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.atguigu.springcloud")
@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MySelfRule.class)
@EnableFeignClients
public class DeptConsumer80_Feign_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer80_Feign_App.class, args);
	}
}
