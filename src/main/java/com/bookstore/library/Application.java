package com.bookstore.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.library.auth.Role;
import com.bookstore.library.entity.auth.RegisterRequest;
import com.bookstore.library.service.impl.AuthenticationService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AuthenticationService service){
		RegisterRequest adminUser = new RegisterRequest();
		adminUser.setName("adminname");
		adminUser.setEmail("admin@mail.bg");
		adminUser.setPassword("1234");
		adminUser.setRole(Role.ADMIN);

		//service.
		return args -> {
			System.out.println("Admin token: " + service.adminRegister(adminUser).getAccessToken());
		};
	}

}
