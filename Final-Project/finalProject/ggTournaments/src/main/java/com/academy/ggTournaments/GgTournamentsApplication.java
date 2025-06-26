package com.academy.ggTournaments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GgTournamentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgTournamentsApplication.class, args);
	}

}
