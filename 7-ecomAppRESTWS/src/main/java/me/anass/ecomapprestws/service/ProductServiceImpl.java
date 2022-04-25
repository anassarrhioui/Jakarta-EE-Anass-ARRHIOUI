package me.anass.ecomapprestws.service;

import lombok.AllArgsConstructor;
import me.anass.ecomapprestws.dto.ProductDTO;
import me.anass.ecomapprestws.entity.Product;
import me.anass.ecomapprestws.mapper.CatalogMappers;
import me.anass.ecomapprestws.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    final private ProductRepository productRepository;

    final private CatalogMappers catalogMappers;

    public ProductDTO save(ProductDTO productDTO){
        Product product = catalogMappers.fromProductDTO(productDTO);
        Product savedProduct = productRepository.save(product);
        return catalogMappers.fromProduct(savedProduct);
    }

    @Override
    public List<ProductDTO> getAll(){
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList
                .forEach( product -> {
                    productDTOList.add(catalogMappers.fromProduct(product));
                });
        return productDTOList;
    }
}
