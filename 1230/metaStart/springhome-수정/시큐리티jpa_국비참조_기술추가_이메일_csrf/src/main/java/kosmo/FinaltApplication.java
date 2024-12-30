package kosmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FinaltApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinaltApplication.class, args);
	}

}
