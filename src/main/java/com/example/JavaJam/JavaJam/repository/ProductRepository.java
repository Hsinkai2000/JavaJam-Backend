package com.example.JavaJam.JavaJam.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import com.example.JavaJam.JavaJam.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByNameAndPrice(String name, double price);

    Iterable<Product> findAll(Sort by);
}
