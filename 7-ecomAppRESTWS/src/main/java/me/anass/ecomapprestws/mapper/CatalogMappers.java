package me.anass.ecomapprestws.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import me.anass.ecomapprestws.dto.CategoryDTO;
import me.anass.ecomapprestws.dto.ProductDTO;
import me.anass.ecomapprestws.entity.Category;
import me.anass.ecomapprestws.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
