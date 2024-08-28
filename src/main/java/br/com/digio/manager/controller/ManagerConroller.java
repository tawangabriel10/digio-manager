package br.com.digio.manager.controller;

import br.com.digio.manager.domain.dto.CustomerDTO;
import br.com.digio.manager.domain.dto.CustomerRecommendationDTO;
import br.com.digio.manager.domain.dto.ShoppingDTO;
import br.com.digio.manager.service.CustomerService;
import br.com.digio.manager.service.ShoppingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ManagerConroller {

    private final ShoppingService shoppingService;
    private final CustomerService customerService;

    @GetMapping("/compras")
    public ResponseEntity<List<ShoppingDTO>> getAllShopping() {
        return ResponseEntity.ok(shoppingService.getAllShopping());
    }

    @GetMapping("/maior_compra/{ano}")
    public ResponseEntity<ShoppingDTO> getMajorShoppingByYear(@PathVariable("ano") Integer year) {
        return ResponseEntity.ok(shoppingService.getMajorShoppingByYear(year));
    }

    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<CustomerDTO>> getTopThreeCustomers() {
        return ResponseEntity.ok(customerService.getTopThreeCustomers());
    }

    @GetMapping("/recomendacao/cliente/tipo")
    public ResponseEntity<List<CustomerRecommendationDTO>> getAllRecommendations() {
        return ResponseEntity.ok(customerService.getAllRecommendations());
    }

}
