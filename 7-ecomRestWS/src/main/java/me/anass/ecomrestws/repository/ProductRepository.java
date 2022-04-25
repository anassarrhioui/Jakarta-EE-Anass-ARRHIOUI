package me.anass.ecomrestws.repository;

import me.anass.ecomrestws.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
