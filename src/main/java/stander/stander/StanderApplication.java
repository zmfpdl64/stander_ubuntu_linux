package stander.stander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(StanderApplication.class, args);
	}

}
