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
public class ShoppingResponse {

    @JsonProperty(value = "codigo")
    private String code;

    @JsonProperty(value = "quantidade")
    private Integer amount;

}
