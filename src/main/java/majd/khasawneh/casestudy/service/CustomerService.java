package majd.khasawneh.casestudy.service;

import majd.khasawneh.casestudy.domain.Customer;
import majd.khasawneh.casestudy.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
}
