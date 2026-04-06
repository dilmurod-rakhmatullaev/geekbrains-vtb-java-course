package com.geekbrains.lesson12.service;

import com.geekbrains.lesson12.entity.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Laptop", 1200.00));
        products.add(new Product(2L, "Mouse", 25.99));
        products.add(new Product(3L, "Keyboard", 89.99));
        products.add(new Product(4L, "Monitor", 350.00));
        products.add(new Product(5L, "Headphones", 79.99));
        products.add(new Product(6L, "Webcam", 129.99));
        products.add(new Product(7L, "USB Cable", 12.99));
        products.add(new Product(8L, "Desk", 299.99));
        products.add(new Product(9L, "Chair", 189.99));
        products.add(new Product(10L, "Mousepad", 9.99));
        System.out.println("ProductService initialized with 10 products");
    }

    public void printAll() {
        System.out.println("\n=== All Products ===");
        products.forEach(System.out::println);
    }

    public Product findByTitle(String title) {
        return products.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}