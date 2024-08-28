package br.com.digio.manager.service;

import br.com.digio.manager.client.CustomerClient;
import br.com.digio.manager.client.dto.CustomerResponse;
import br.com.digio.manager.client.dto.ProductResponse;
import br.com.digio.manager.client.dto.ShoppingResponse;
import br.com.digio.manager.domain.dto.CustomerDTO;
import br.com.digio.manager.domain.dto.CustomerRecommendationDTO;
import br.com.digio.manager.domain.dto.RecommendationDTO;
import br.com.digio.manager.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;
    private final ProductRepository productRepository;

    public List<CustomerDTO> getTopThreeCustomers() {
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
            .amount(this.getAmountProducts(customerResponse, productResponse))
            .totalPrice(0D)
            .build();
    }

    public List<CustomerRecommendationDTO> getAllRecommendations() {
        return customerClient.getCustomers().stream()
            .map(this::getRecommendation)
            .toList();
    }

    private CustomerRecommendationDTO getRecommendation(final CustomerResponse customerResponse) {
        final Set<String> types = productRepository.getProducts().stream()
            .map(ProductResponse::getType)
            .collect(Collectors.toSet());

        var customerRecommendationDTO = CustomerRecommendationDTO.builder()
            .name(customerResponse.getName())
            .document(customerResponse.getDocument())
            .recommendations(new ArrayList<>())
            .build();
        for (var type : types) {
            final var products = productRepository.getProducts().stream()
                .filter(p -> type.equalsIgnoreCase(p.getType()))
                .toList();

            customerResponse.getShopping().stream()
                .filter(shopping -> products.stream()
                    .anyMatch(p -> p.getCode().equalsIgnoreCase(shopping.getCode())))
                .max(Comparator.comparing(ShoppingResponse::getAmount))
                .flatMap(shopping -> products.stream()
                    .filter(p -> shopping.getCode().equalsIgnoreCase(p.getCode()))
                    .findFirst())
                .ifPresent(product -> customerRecommendationDTO.getRecommendations()
                        .add(RecommendationDTO.builder()
                            .code(product.getCode())
                            .type(product.getType())
                            .build()));
        }
        return customerRecommendationDTO;
    }

    private Integer getAmountProducts(final CustomerResponse customerResponse, final ProductResponse productResponse) {
        return customerResponse.getShopping().stream()
            .filter(s -> productResponse.getCode().equalsIgnoreCase(s.getCode()))
            .reduce(0, (result, s) -> result + s.getAmount(), Integer::sum);
    }
}
