package br.com.digio.manager.domain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRecommendationDTO {

    private String name;
    private String document;
    private List<RecommendationDTO> recommendations;
}
