package br.com.digio.manager.repository;

import br.com.digio.manager.client.ProductClient;
import br.com.digio.manager.client.dto.ProductResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private List<ProductResponse> products;

    @Autowired
    private ProductClient productClient;

    public ProductRepository() {
        this.products = productClient.getProducts();
    }

    public List<ProductResponse> getProducts() {
        return this.getProducts();
    }
}
