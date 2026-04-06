package com.geekbrains.lesson12.service;

import org.springframework.stereotype.Component;

@Component
public class OrderService {

    public void createOrder(Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cannot create order: cart is empty!");
            return;
        }

        System.out.println("\n=== ORDER CONFIRMATION ===");
        System.out.println("Items purchased:");
        cart.getItems().forEach(item ->
                System.out.printf("  %-20s $%.2f%n", item.getTitle(), item.getCost())
        );
        System.out.println("-".repeat(30));
        System.out.printf("TOTAL AMOUNT: $%.2f%n", cart.getTotalCost());
        System.out.println("=".repeat(30));
        System.out.println("Order placed successfully!");

        cart.clear();
    }
}