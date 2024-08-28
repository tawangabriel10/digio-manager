package br.com.digio.manager.repository;

import br.com.digio.manager.client.ProductClient;
import br.com.digio.manager.client.dto.ProductResponse;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private List<ProductResponse> products;
    private final ProductClient productClient;

    public ProductRepository(ProductClient productClient) {
        this.productClient = productClient;
        this.products = this.productClient.getProducts();
    }

    public List<ProductResponse> getProducts() {
        return this.products;
    }
}
