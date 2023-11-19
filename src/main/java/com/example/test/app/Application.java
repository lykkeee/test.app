package com.example.test.app;

import com.example.test.app.model.auth.Role;
import com.example.test.app.model.enums.Roles;
import com.example.test.app.repository.RoleRepository;
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
	CommandLineRunner run(RoleRepository roleRepository) {
		return args -> {
			roleRepository.save(new Role(Roles.USER.toString()));
		};
	}

}
