package com.bookstore.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.library.auth.Role;
import com.bookstore.library.entity.auth.AuthenticationResponse;
import com.bookstore.library.entity.auth.RegisterRequest;
import com.bookstore.library.service.impl.AuthenticationService;

@SpringBootApplication
public class Application {

	private static final Logger logger = LogManager.getLogger("bookstore");
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AuthenticationService service){
		RegisterRequest adminUser = new RegisterRequest();
		adminUser.setName("Administrator");
		adminUser.setEmail("admin");
		adminUser.setPassword("admin");
		adminUser.setRole(Role.ADMIN);

		return args -> {
			AuthenticationResponse response = service.adminRegister(adminUser);
			if (response != null) {
				logger.info("Admin created");
			}
		};
	}

}
