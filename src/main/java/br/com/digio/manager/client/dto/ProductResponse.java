package br.com.digio.manager.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    @JsonProperty(value = "codigo")
    private String code;

    @JsonProperty(value = "tipo_vinho")
    private String type;

    @JsonProperty(value = "preco")
    private Double price;

    @JsonProperty(value = "safra")
    private String harvest;

    @JsonProperty(value = "ano_compra")
    private Integer year;
}
