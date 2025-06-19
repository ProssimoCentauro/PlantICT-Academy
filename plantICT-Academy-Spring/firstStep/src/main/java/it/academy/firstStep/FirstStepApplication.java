package it.academy.firstStep;

import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "it.academy.firstStep")
public class FirstStepApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstStepApplication.class, args);

	}
}
