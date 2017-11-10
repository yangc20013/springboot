package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.springboot.config.AppConfig;

@EnableConfigurationProperties({ AppConfig.class })
@SpringBootApplication
public class ApplicationStart
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ApplicationStart.class, args);
    }
}
