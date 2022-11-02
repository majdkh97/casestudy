package majd.khasawneh.casestudy.rest;

import majd.khasawneh.casestudy.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;


@RestController
@RequestMapping("/customer")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/balance/{id}")
    public Double getCumulativeBalance(@PathVariable Long id) {
        return customerService.getCumulativeBalance(id);
    }

    @GetMapping("/balance/{id}/month")
    public Double getMonthlyBalance(@PathVariable Long id, @RequestParam Month month) {
        LocalDate date = LocalDate.of(Year.now().getValue(),month.getValue(),1);
        return customerService.getMonthlyBalance(id,date);
    }

}
