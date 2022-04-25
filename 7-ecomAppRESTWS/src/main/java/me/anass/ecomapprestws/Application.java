package me.anass.ecomapprestws;

import me.anass.ecomapprestws.entity.Category;
import me.anass.ecomapprestws.entity.Product;
import me.anass.ecomapprestws.repository.CategoryRepository;
import me.anass.ecomapprestws.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ){
        return args -> {
            Stream
                    .of("Computer", "Printer", "Phone")
                    .forEach(name -> {
                        categoryRepository.save(Category.builder().nom(name).build());
                    });
            categoryRepository
                    .findAll()
                    .forEach(category -> {
                        for (int i=0; i<5; i++){
                            productRepository
                                    .save(
                                            Product
                                                    .builder()
                                                    .id(UUID.randomUUID().toString())
                                                    .name(category.getNom()+" "+i)
                                                    .price(Math.random()*5000)
                                                    .quantity(Math.random()*50)
                                                    .category(category)
                                                    .build()
                                    );
                        }
                    });
        };
    }
}
