package kz.cleangov.cleangov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"kz.cleangov"})
public class CleangovApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleangovApplication.class, args);
	}

}
