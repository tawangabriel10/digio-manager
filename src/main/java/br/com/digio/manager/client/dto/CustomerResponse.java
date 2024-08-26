package br.com.digio.manager.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "cpf")
    private String document;

    @JsonProperty(value = "compras")
    private List<ShoppingResponse> shopping;

}
