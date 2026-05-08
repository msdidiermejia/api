package com.bun.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SrvApiRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrvApiRegisterApplication.class, args);
	}

}
