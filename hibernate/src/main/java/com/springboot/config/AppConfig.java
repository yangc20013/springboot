package com.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
public class AppConfig {
	//获取application-dev.properties中config.name   dev环境。在application.properties spring.profiles.active
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
