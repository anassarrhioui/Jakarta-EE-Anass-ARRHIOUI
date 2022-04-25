package me.anass.ecomrestws;

import me.anass.ecomrestws.entity.Product;
import me.anass.ecomrestws.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EcomRestWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomRestWsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            ProductRepository productRepository
    ){
        return args -> {
            List<Product> productList = new ArrayList<>();
            Stream
                    .of("Computer", "Printer", "Phone")
                    .forEach(pName -> {
                        productList.add(
                                new Product(UUID.randomUUID().toString(), pName, Math.random()*8000, Math.random()*100)
                        );
                    });
            productRepository.saveAll(productList);
        };
    }

}
