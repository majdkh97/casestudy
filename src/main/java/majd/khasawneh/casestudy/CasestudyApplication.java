package majd.khasawneh.casestudy;

import majd.khasawneh.casestudy.domain.Customer;
import majd.khasawneh.casestudy.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CasestudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasestudyApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerService customerService){
		//Initializing Data
		return args -> {
			Customer customer = new Customer();
			customer.setEmail("majd.motaz@yahoo.com");
			customer.setName("Majd");
			customer.setBalance(1000D);

			customerService.save(customer);
		};
	}
}
