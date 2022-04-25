package me.anass.ecomapprestws.service;

import lombok.AllArgsConstructor;
import me.anass.ecomapprestws.dto.ProductDTO;
import me.anass.ecomapprestws.entity.Product;
import me.anass.ecomapprestws.mapper.CatalogMappers;
import me.anass.ecomapprestws.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductService {
    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> getAll();
}
