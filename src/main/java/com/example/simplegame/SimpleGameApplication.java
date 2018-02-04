package com.example.simplegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

@SpringBootApplication
@EnableJpaRepositories
public class SimpleGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleGameApplication.class, args);
	}
}
