package br.com.digio.manager.repository;

import br.com.digio.manager.client.CustomerClient;
import br.com.digio.manager.client.dto.CustomerResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private List<CustomerResponse> customers;
    private final CustomerClient customerClient;

    public CustomerRepository(CustomerClient customerClient) {
        this.customerClient = customerClient;
        this.customers = this.customerClient.getCustomers();
    }

    public List<CustomerResponse> getCustomers() {
        return customers;
    }
}
