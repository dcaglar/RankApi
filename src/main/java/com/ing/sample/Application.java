package com.ing.sample;

import com.ing.sample.service.RankingUtilService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialize(RankingUtilService rankingUtilService) {
		try {
			rankingUtilService.initDatabase();
			return args -> {
			};
		} catch (Exception e) {
			System.out.println("Error occured when initializing database");
			System.exit(0);
		} finally {
			return args -> {
				System.out.println("initialize method finished");
			};
		}
	}
}
