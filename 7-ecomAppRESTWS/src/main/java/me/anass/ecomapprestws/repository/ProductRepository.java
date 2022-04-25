package me.anass.ecomapprestws.repository;

import me.anass.ecomapprestws.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, String> {
}
