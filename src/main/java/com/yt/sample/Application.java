package com.yt.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application {

	/*
	 * Author: Utkarsh Kumar Sharma
	 * Date: 11-10-2020
	 * License: GPL
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
