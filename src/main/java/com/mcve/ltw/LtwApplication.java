package com.mcve.ltw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LtwApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtwApplication.class, args);
	}

	/**
	 * --add-opens=java.base/java.lang=ALL-UNNAMED -javaagent:C:\apache-tomcat-10.1.13\lib\spring-instrument-6.1.8.jar
	 */

}
