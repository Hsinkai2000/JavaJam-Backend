package com.example.JavaJam.JavaJam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.JavaJam.JavaJam.model.Order;
import com.example.JavaJam.JavaJam.model.Report;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("SELECT o.product.name AS name, SUM(o.quantity) AS totalQuantity, SUM(o.price) AS totalSales FROM Order o WHERE o.createdAt = :currentDate GROUP BY name ORDER BY totalQuantity DESC")
    List<Report> findOrdersByCreatedAtProduct(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT o.product.category AS category, SUM(o.quantity) AS totalQuantity, SUM(o.price) AS totalSales FROM Order o WHERE o.createdAt = :currentDate GROUP BY category ORDER BY totalQuantity DESC")
    List<Report> findOrdersByCreatedAtCategory(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT o.product.name AS name, o.product.category AS category, SUM(o.quantity) AS totalQuantity, SUM(o.price) AS totalSales FROM Order o WHERE o.createdAt = :currentDate GROUP BY name, category ORDER BY totalQuantity DESC")
    List<Report> findOrdersByCreatedAt(@Param("currentDate") LocalDate currentDate);
}
