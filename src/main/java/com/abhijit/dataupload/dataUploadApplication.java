package com.abhijit.dataupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class dataUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(dataUploadApplication.class, args);
	}

}
