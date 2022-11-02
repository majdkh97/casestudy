package majd.khasawneh.casestudy.service;

import majd.khasawneh.casestudy.domain.Customer;
import majd.khasawneh.casestudy.domain.Transaction;
import majd.khasawneh.casestudy.domain.TransactionType;
import majd.khasawneh.casestudy.exceptions.CustomerServiceException;
import majd.khasawneh.casestudy.repository.CustomerRepository;
import majd.khasawneh.casestudy.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    public CustomerService(CustomerRepository customerRepository,
                           TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Double getCumulativeBalance(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty())
            throw new CustomerServiceException("Customer not found by the given ID " + id);

        return customer.get().getBalance();
    }

    public Double getMonthlyBalance(Long id,LocalDate date) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty())
            throw new CustomerServiceException("Customer not found by the given ID " + id);

        LocalDate endOfMonth = date.with(lastDayOfMonth());
        List<Transaction> transactionList = transactionRepository.findByCustomer_IdAndDateBefore(id,endOfMonth);

        double depositSum = transactionList.stream()
                .filter(transaction -> transaction.getType().equals(TransactionType.DEPOSIT))
                .reduce(0D,(sum,transaction) -> sum + transaction.getAmount(), Double::sum);

        double withdrawSum = transactionList.stream()
                .filter(transaction -> transaction.getType().equals(TransactionType.WITHDRAW))
                .reduce(0D,(sum,transaction) -> sum + transaction.getAmount(), Double::sum);

        return depositSum - withdrawSum;
    }
}
