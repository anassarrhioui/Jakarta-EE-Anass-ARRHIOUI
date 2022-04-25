package me.anass.ecomapprestws.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    private String id;

    private String name;

    private Double price;

    private Double quantity;

    @ManyToOne
    private Category category;
}
