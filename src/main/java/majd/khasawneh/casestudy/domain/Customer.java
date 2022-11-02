package majd.khasawneh.casestudy.domain;

import majd.khasawneh.casestudy.exceptions.CustomerServiceException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "balance", nullable = false)
    private Double balance = 0D;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Transaction> transactions = new HashSet<>();

    public void addTransaction(Transaction transaction) {
        if (transaction.getType().equals(TransactionType.DEPOSIT)) {
            transactions.add(transaction);
            this.balance = this.balance + transaction.getAmount();
            transaction.setCustomer(this);
        } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {
            if (this.balance - transaction.getAmount() < 0)
                throw new CustomerServiceException("not enough balance");

            transactions.add(transaction);
            this.balance = this.balance - transaction.getAmount();
            transaction.setCustomer(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

}
