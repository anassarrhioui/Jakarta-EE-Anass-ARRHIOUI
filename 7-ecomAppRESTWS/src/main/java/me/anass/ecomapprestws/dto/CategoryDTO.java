package me.anass.ecomapprestws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.anass.ecomapprestws.entity.Product;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {
    private Long id;
    private String nom;

    private List<Product> productList;
}
