package com.mcve.ltw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

//@EnableLoadTimeWeaving
@SpringBootApplication
public class LtwApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtwApplication.class, args);
	}

}
