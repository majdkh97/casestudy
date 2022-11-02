package majd.khasawneh.casestudy;

import majd.khasawneh.casestudy.domain.Customer;
import majd.khasawneh.casestudy.domain.Transaction;
import majd.khasawneh.casestudy.domain.TransactionType;
import majd.khasawneh.casestudy.repository.CustomerRepository;
import majd.khasawneh.casestudy.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class CasestudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasestudyApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository, CustomerService customerService) {
        //Initializing Data
        return args -> {
            Customer customer = new Customer();
            customer.setEmail("majd.motaz@yahoo.com");
            customer.setName("Majd");
            customer.addTransaction(new Transaction(1000D, LocalDate.of(2022, Month.MAY, 24), TransactionType.DEPOSIT));
            customer.addTransaction(new Transaction(500D, LocalDate.of(2022, Month.MAY, 15), TransactionType.WITHDRAW));
            customer.addTransaction(new Transaction(1000D, LocalDate.of(2022, Month.MAY, 21), TransactionType.DEPOSIT));
            customer.addTransaction(new Transaction(500D, LocalDate.of(2022, Month.JUNE, 15), TransactionType.WITHDRAW));
            customerRepository.save(customer);
            System.out.println(customerService.getMonthlyBalance(1L, LocalDate.of(2022, Month.MAY, 1)));

            Customer customer2 = new Customer();
            customer2.setEmail("ahmed.motaz@yahoo.com");
            customer2.setName("Ahmed");
            customer2.addTransaction(new Transaction(750D, LocalDate.of(2022, Month.MAY, 24), TransactionType.DEPOSIT));
            customerRepository.save(customer2);
            System.out.println(customerService.getMonthlyBalance(6L, LocalDate.of(2022, Month.MAY, 1)));
        };
    }
}
