package com.geekbrains.lesson12.service;

import com.geekbrains.lesson12.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<Product> items;
    private double totalCost;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void add(Product product) {
        if (product != null) {
            items.add(product);
            totalCost += product.getCost();
            System.out.println("Added: " + product.getTitle() + " (cost: " + product.getCost() + ")");
        }
    }

    public void remove(Product product) {
        if (items.remove(product)) {
            totalCost -= product.getCost();
            System.out.println("Removed: " + product.getTitle());
        }
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void clear() {
        items.clear();
        totalCost = 0.0;
    }

    public void printCart() {
        System.out.println("\n=== Shopping Cart ===");
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
        } else {
            items.forEach(p -> System.out.println("  " + p.getTitle() + " - $" + p.getCost()));
            System.out.println("Total: $" + totalCost);
        }
    }
}