package com.cloud.mic_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.cloud")
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan("com.cloud.commons.dao")
public class MicUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicUserApplication.class, args);
	}

}
