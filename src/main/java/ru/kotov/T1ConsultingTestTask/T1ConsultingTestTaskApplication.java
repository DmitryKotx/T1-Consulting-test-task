package ru.kotov.T1ConsultingTestTask;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Test Task",
				version="1.0.0",
				description = "This project allows you to get the frequency of characters in a string",
				contact = @Contact(
						name = "Dmitry Kotov",
						email = "dskotov003@gmail.com"
				)
		)
)
public class T1ConsultingTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(T1ConsultingTestTaskApplication.class, args);
	}

}
