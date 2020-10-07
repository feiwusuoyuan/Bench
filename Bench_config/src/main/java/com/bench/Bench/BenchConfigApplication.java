package com.bench.Bench;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//开启声明式服务调用
//服务降级注解
@EnableCircuitBreaker
//开启Redis会话共享
//@EnableRedisHttpSession
@SpringBootApplication
public class BenchConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenchConfigApplication.class, args);
	}


	/**
	 * 	定义 RestTemplate  Bean
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
