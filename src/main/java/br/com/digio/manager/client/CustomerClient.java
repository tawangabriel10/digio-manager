package br.com.digio.manager.client;

import br.com.digio.manager.client.dto.CustomerResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "customer-client", url = "${digio-base-url-storage}")
public interface CustomerClient {

    @GetMapping("/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
    List<CustomerResponse> getCustomers();
}
