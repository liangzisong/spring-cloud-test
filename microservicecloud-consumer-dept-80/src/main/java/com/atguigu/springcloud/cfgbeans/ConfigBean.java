package com.atguigu.springcloud.cfgbeans;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{ 
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	//因为已经自定义Rule并扫描了,所以不用在这里放入bean了
	@Bean
	public IRule myRule(){
		return new RandomRule();
	}

}