package com.example.JavaJam.JavaJam.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JavaJam.JavaJam.model.Order;
import com.example.JavaJam.JavaJam.model.OrderRequest;
import com.example.JavaJam.JavaJam.model.Product;
import com.example.JavaJam.JavaJam.model.Report;
import com.example.JavaJam.JavaJam.repository.OrderRepository;
import com.example.JavaJam.JavaJam.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository; // Inject ProductRepository

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository; // Initialize ProductRepository
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitOrder(@RequestBody OrderRequest data) {
        try {
            logger.debug("Received OrderRequestss: {}", data.toString());

            List<Order> orderList = new ArrayList<Order>();

            if (data.getJustJavaQuantity() != 0) {
                logger.debug("justJava price: {} jsut java qty: {}", data.getJustJava(), data.getJustJavaQuantity());
                Product justJavaProduct = productRepository.findByNameAndPrice("Just Java",
                        data.getJustJava()).get();
                logger.debug("justJavaProduct: {}", justJavaProduct);
                orderList.add(new Order(justJavaProduct, data.getJustJavaQuantity(), data.calcJustJavaPrice()));
            }
            if (data.getCafeAuLaitQuantity() != 0) {
                logger.debug("cafeAuLaitProduct price: {} cafeAuLaitProduct qty: {}", data.getCafeAuLait(),
                        data.getCafeAuLaitQuantity());
                Product cafeAuLaitProduct = productRepository.findByNameAndPrice("Cafe au Lait",
                        data.getCafeAuLait()).get();
                logger.debug("cafeAuLaitProduct: {}", cafeAuLaitProduct);
                orderList.add(new Order(cafeAuLaitProduct, data.getCafeAuLaitQuantity(), data.calcCafeAuLaitPrice()));
            }
            if (data.getIcedCappuccinoQuantity() != 0) {
                logger.debug("icedCappuccinoProduct price: {} icedCappuccinoProduct qty: {}", data.getIcedCappuccino(),
                        data.getIcedCappuccinoQuantity());
                Product icedCappuccinoProduct = productRepository.findByNameAndPrice("Iced Cappuccino",
                        data.getIcedCappuccino()).get();
                orderList.add(new Order(icedCappuccinoProduct, data.getIcedCappuccinoQuantity(),
                        data.calcIcedCappuccinoPrice()));

            }
            for (Order order : orderList) {
                orderRepository.save(order);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Orders submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to submit orders: " + e.getMessage());
        }
    }

    @GetMapping("/reportByProducts")
    public ResponseEntity<List<Report>> reportByProducts() {
        try {

            LocalDate today = LocalDate.now();

            List<Report> reports = orderRepository.findOrdersByCreatedAtProduct(today);

            return ResponseEntity.status(HttpStatus.OK).body(reports);
        } catch (Exception e) {
            logger.error("Failed to generate report: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<Report>());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/reportByCategory")
    public ResponseEntity<List<Report>> reportByCategory() {
        try {

            LocalDate today = LocalDate.now();

            List<Report> reports = orderRepository.findOrdersByCreatedAtCategory(today);

            return ResponseEntity.status(HttpStatus.OK).body(reports);
        } catch (

        Exception e) {
            logger.error("Failed to generate report: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<Report>());
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/reportFull")
    public ResponseEntity<List<Report>> reportByProductAndCategory() {
        try {

            LocalDate today = LocalDate.now();

            List<Report> reports = orderRepository.findOrdersByCreatedAt(today);

            return ResponseEntity.status(HttpStatus.OK).body(reports);
        } catch (

        Exception e) {
            logger.error("Failed to generate report: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<Report>());
        }
    }
}
