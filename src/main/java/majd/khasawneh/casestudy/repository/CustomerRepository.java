package majd.khasawneh.casestudy.repository;

import majd.khasawneh.casestudy.domain.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Override
    @EntityGraph(attributePaths = {"transactions"})
    Optional<Customer> findById(Long aLong);

}
