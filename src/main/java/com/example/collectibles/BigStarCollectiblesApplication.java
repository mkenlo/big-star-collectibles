package com.example.collectibles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
public class BigStarCollectiblesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigStarCollectiblesApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor(){
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
		threadPoolExecutor.setCorePoolSize(2);
		threadPoolExecutor.setMaxPoolSize(2);
		threadPoolExecutor.setQueueCapacity(500);
		threadPoolExecutor.setThreadNamePrefix("Spring-Async");
		threadPoolExecutor.initialize();
		return threadPoolExecutor;
	}

}
