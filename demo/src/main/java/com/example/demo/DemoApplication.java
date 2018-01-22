package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@EnableAutoConfiguration
@SpringBootApplication
public class DemoApplication implements EmbeddedServletContainerCustomizer{
//	CREATE TABLE `user_logout` (
//			   `id` int(11) NOT NULL AUTO_INCREMENT,
//			   `username` varchar(50) DEFAULT NULL,
//			   `created_by` varchar(50) DEFAULT NULL,
//			   `created_time` datetime DEFAULT NULL,
//			   PRIMARY KEY (`id`)
//			 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
//	CREATE TABLE `account` (
//			   `id` int(11) NOT NULL AUTO_INCREMENT,
//			   `email` varchar(255) DEFAULT NULL,
//			   `password` varchar(255) DEFAULT NULL,
//			   `username` varchar(255) DEFAULT NULL,
//			   PRIMARY KEY (`id`)
//			 ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
			 
			 
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer arg0) {
		arg0.setPort(8089);		
	}
}
