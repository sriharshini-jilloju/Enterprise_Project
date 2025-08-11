package com.stocktrading.marketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient

@SpringBootApplication
public class MarketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketServiceApplication.class, args);
	}

}
