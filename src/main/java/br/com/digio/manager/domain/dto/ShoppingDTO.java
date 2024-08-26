package br.com.digio.manager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingDTO {

    private String name;
    private String document;
    private String code;
    private String type;
    private Double price;
    private String harvest;
    private Integer year;
    private Integer amount;
    private Double totalPrice;
}
