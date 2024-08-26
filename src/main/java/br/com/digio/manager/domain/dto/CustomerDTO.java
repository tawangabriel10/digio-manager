package br.com.digio.manager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String name;
    private String document;
    private Double price;
    private Integer amount;
    private Double totalPrice;

}
