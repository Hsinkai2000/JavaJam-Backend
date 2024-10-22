package com.example.JavaJam.JavaJam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JavaJam.JavaJam.model.Product;
import com.example.JavaJam.JavaJam.repository.ProductRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.Map;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products")
    public Iterable<Product> getProductPrice() {
        return this.productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updatePrices")
    public String updateProductPrice(@RequestBody Map<Integer, Double> priceUpdates) {
        logger.debug("Received OrderRequestss: {}", priceUpdates.toString());
        StringBuilder result = new StringBuilder();

        for (Map.Entry<Integer, Double> entry : priceUpdates.entrySet()) {
            Integer productId = entry.getKey();
            Double newPrice = entry.getValue();

            // Find the product by ID
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                // Update the product's price
                product.setPrice(newPrice);
                productRepository.save(product); // Save the updated product
                result.append("Updated product ID ").append(productId).append(" to price ").append(newPrice)
                        .append(". ");
            } else {
                result.append("Product ID ").append(productId).append(" not found. ");
            }
        }

        return result.toString(); // Return a summary of updates
    }
}
