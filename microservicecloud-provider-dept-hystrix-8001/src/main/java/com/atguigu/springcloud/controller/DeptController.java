package com.atguigu.springcloud.controller;

import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;

@RestController
public class DeptController
{
	@Autowired
	private DeptService service;
	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept)
	{
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	//抛出异常会调用这个方法阿
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id)
	{
		Dept dept = service.get(id);
		if(dept == null){
			throw new RuntimeException("id不存在");
		}
		return dept;
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}

	
//	@Autowired
//	private DiscoveryClient client;
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery()
	{
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id){
		Dept dept = new Dept();
		{
			dept.setDeptno(id);
			dept.setDb_source("no this database in mysql");
			dept.setDname("this id "+id+" not info");
		}
		return dept;
	}

}
