package com.project.CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

public class CrudApplication {

	public static void main(String[] args) {   SpringApplication.run(CrudApplication.class, args);}

	@EnableAsync
	public class AsyncFileHandlingApplication {
		public static void main(String[] args) {
			SpringApplication.run(AsyncFileHandlingApplication.class, args);
		}
	}

}






