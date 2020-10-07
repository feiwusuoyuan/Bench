package com.bench.Bench;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCircuitBreaker
@MapperScan("com.bench.Bench.dao")
@EnableFeignClients
@EntityScan("com.bench.bean")
public class BenchBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenchBackApplication.class, args);
    }

}
