package com.buddget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index.html");
		registry.addViewController("/login").setViewName("index.html");
		registry.addViewController("/sign-up").setViewName("index.html");
		registry.addViewController("/profile").setViewName("index.html");
		registry.addViewController("/favicon.ico").setViewName("index.html");
	}
}
