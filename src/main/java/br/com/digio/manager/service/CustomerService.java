package br.com.digio.manager.service;

import br.com.digio.manager.client.CustomerClient;
import br.com.digio.manager.client.dto.CustomerResponse;
import br.com.digio.manager.client.dto.ProductResponse;
import br.com.digio.manager.client.dto.ShoppingResponse;
import br.com.digio.manager.domain.dto.CustomerDTO;
import br.com.digio.manager.domain.dto.ShoppingDTO;
import br.com.digio.manager.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;
    private final ProductRepository productRepository;

    public List<CustomerDTO> getTopTreeCustomers() {
        var customerDTOS = new ArrayList<CustomerDTO>();
        for (final var product : productRepository.getProducts()) {
            customerDTOS.addAll(customerClient.getCustomers().stream()
                .map(customerResponse -> {
                    CustomerDTO customerDTO = this.converterCustomerDTO(customerResponse, product);
                    customerDTO.setTotalPrice(customerDTO.getPrice() * customerDTO.getAmount());
                    return customerDTO;
                })
                .toList());
        }
        return customerDTOS.stream()
            .sorted(Comparator.comparing(CustomerDTO::getTotalPrice).reversed())
            .limit(3)
            .toList();
    }

    private CustomerDTO converterCustomerDTO(final CustomerResponse customerResponse, final ProductResponse productResponse) {
        return CustomerDTO.builder()
            .name(customerResponse.getName())
            .document(customerResponse.getDocument())
            .price(productResponse.getPrice())
            .amount(customerResponse.getShopping().stream()
                .filter(s -> productResponse.getCode().equalsIgnoreCase(s.getCode()))
                .reduce(0, (result, s) -> result + s.getAmount(), Integer::sum))
            .totalPrice(0D)
            .build();
    }
}
