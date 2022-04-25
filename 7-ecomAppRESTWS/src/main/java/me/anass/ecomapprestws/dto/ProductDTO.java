package me.anass.ecomapprestws.dto;

import lombok.*;
import me.anass.ecomapprestws.entity.Category;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private String id;

    private String name;

    private Double price;

    private Double quantity;

    private CategoryDTO category;
}
