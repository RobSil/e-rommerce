package com.robsil.erommerce;

import com.robsil.erommerce.service.impl.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ERommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ERommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InitService initService) {
		return args -> initService.checkIfAdminUserCreatedIfNotCreateThen();
	}

}
