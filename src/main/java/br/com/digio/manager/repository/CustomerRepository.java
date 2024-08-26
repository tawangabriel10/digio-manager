package br.com.digio.manager.repository;

import br.com.digio.manager.client.CustomerClient;
import br.com.digio.manager.client.dto.CustomerResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private List<CustomerResponse> customers;

    @Autowired
    private CustomerClient customerClient;

    public CustomerRepository() {
        this.customers = customerClient.getCustomers();
    }

    public List<CustomerResponse> getCustomers() {
        return customers;
    }
}
