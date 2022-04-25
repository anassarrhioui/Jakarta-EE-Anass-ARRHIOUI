package me.anass.ecomapprestws.repository;

import me.anass.ecomapprestws.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
