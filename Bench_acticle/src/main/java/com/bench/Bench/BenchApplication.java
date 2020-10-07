package com.bench.Bench;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableCircuitBreaker
@MapperScan("com.bench.Bench.dao")
@EnableFeignClients
public class BenchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenchApplication.class, args);
	}

}
