package br.com.digio.manager.service;

import br.com.digio.manager.client.dto.CustomerResponse;
import br.com.digio.manager.client.dto.ShoppingResponse;
import br.com.digio.manager.domain.dto.ShoppingDTO;
import br.com.digio.manager.domain.exception.BusinessException;
import br.com.digio.manager.repository.CustomerRepository;
import br.com.digio.manager.repository.ProductRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public List<ShoppingDTO> getAllShopping() {
        return customerRepository.getCustomers().stream()
            .flatMap(customer -> customer.getShopping().stream().map(shopping -> this.converterShoppingDTO(customer, shopping)))
            .sorted(Comparator.comparing(ShoppingDTO::getPrice))
            .collect(Collectors.toList());
    }

    public ShoppingDTO getMajorShoppingByYear(final String year) {
        return getAllShopping().stream().collect(
            Collectors.collectingAndThen(
                Collectors.groupingBy(ShoppingDTO::getYear),
                map -> map.values().stream()
                    .map(l -> l.stream().max(Comparator.comparing(ShoppingDTO::getTotalPrice)).orElse(null))
                    .filter(Objects::nonNull)))
            .filter(shopping -> year.equals(shopping.getYear()))
            .findFirst()
            .orElseThrow(() -> new BusinessException(String.format("Nenhuma compra encontrada para o ano %s", year)));
    }

    private ShoppingDTO converterShoppingDTO(final CustomerResponse customerResponse, final ShoppingResponse shoppingResponse) {
        final var product = productRepository.getProducts().stream()
            .filter(p -> shoppingResponse.getCode().equalsIgnoreCase(p.getCode()))
            .findFirst()
            .get();
        return ShoppingDTO.builder()
            .name(customerResponse.getName())
            .document(customerResponse.getDocument())
            .code(product.getCode())
            .type(product.getType())
            .price(product.getPrice())
            .harvest(product.getHarvest())
            .year(product.getYear())
            .amount(shoppingResponse.getAmount())
            .totalPrice(product.getPrice() * shoppingResponse.getAmount())
            .build();
    }
}
