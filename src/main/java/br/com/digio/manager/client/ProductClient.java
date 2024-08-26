package br.com.digio.manager.client;

import br.com.digio.manager.client.dto.ProductResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-client", url = "${digio-base-url-storage}")
public interface ProductClient {

    @GetMapping("/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
    List<ProductResponse> getProducts();
}
