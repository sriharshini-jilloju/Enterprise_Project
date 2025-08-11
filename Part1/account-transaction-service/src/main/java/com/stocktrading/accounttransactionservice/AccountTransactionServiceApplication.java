package com.stocktrading.accounttransactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class AccountTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountTransactionServiceApplication.class, args);
	}

}
