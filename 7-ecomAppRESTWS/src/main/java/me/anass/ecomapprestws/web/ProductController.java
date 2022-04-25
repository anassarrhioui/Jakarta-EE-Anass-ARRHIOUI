package me.anass.ecomapprestws.web;

import lombok.AllArgsConstructor;
import me.anass.ecomapprestws.entity.Product;
import me.anass.ecomapprestws.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable String id){
        return productRepository.getById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String id){
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
    }



}
