# JEE : Spring MVC

## REST API

**Date**: *28/03/2022*

Dans la derniere partie nous avons vu comment faire une application web avec un rendu cote serveur. Dans cette partie nous allons voir comment rendre des donnees sous la forme JSON au client Http qui demande une resource, cela veut dire que nous allons mettre en place une API REST.

REST (representational state transfer) est un style d'architecture logicielle définissant un ensemble de contraintes à utiliser pour créer des services web. Les services web conformes au style d'architecture REST, aussi appelés services web RESTful


**Dépendance dans POM.xml**

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

** Configuration dans application.properties **

```properties
server.port=8081
spring.datasource.url=jdbc:h2:mem:product_db
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```

## Connecteur JDBC pour la base de données MySql

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>
```

**Configuration dans application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_patient?serverTimezone=UTC
spring.datasource.username = root
spring.datasource.password = motdepasse
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = create
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
```

## Entités JPA

### Product

```java
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
```

### Category

```java
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}

```
## Controlleur
Dans le cas ou on cree un Web Service REST, les controlleurs vont retourner des listes et des objects directement, ces dernier vont etre intercepter par **DespatcherServlet**, qui appeler jackson pour les serialises.
```java
@PostMapping
    public Product saveProduct(@RequestBody Product product){
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }
```

![Drag Racing](mvc-context-hierarchy.png)

Dans le bonnes pratqiues il faut mieux passe par la couche service qui va effectuer les traitements.


## DTOs

### Category DTP
```java
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
}
```

### Product DTO
```java
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

```

### Mappers
Pour fair un mapping Object Object on peut utiliser soit nos propre mappers comme notre cas ou bien utilise un librairie qui va se charge de faire cela, comme Mapstruct

```java
@Component
public class CatalogMappers {

    public ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setCategory(fromCategory(product.getCategory()));
        return  productDTO;
    }

    public CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return  categoryDTO;
    }

    public Product fromProductDTO(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
```
