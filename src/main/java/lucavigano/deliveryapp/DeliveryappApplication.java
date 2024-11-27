package lucavigano.deliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DeliveryappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryappApplication.class, args);
	}

	@Bean
	PasswordEncoder getBCrypt() {
		return new BCryptPasswordEncoder(12);
	}

}
