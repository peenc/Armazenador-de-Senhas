package com.pedropc.senhas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages={"com.pedropc.senhas.controllers","com.pedropc.senhas.services"})
@EnableJpaRepositories("com.pedropc.senhas.repositories")	
public class SenhasApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SenhasApplication.class, args);
		
	}

}
