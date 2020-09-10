package ro.scoalainformala.covidhelp.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.scoalainformala.covidhelp.webapp.repository.AccountRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
