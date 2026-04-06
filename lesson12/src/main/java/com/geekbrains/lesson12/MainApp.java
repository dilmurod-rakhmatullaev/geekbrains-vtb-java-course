package com.geekbrains.lesson12;

import com.geekbrains.lesson12.config.AppConfig;
import com.geekbrains.lesson12.entity.Product;
import com.geekbrains.lesson12.service.Cart;
import com.geekbrains.lesson12.service.OrderService;
import com.geekbrains.lesson12.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean(ProductService.class);
        OrderService orderService = context.getBean(OrderService.class);

        Cart cart1 = context.getBean(Cart.class);
        Cart cart2 = context.getBean(Cart.class);

        System.out.println("=== Welcome to Spring Shop ===");

        productService.printAll();

        System.out.println("\n=== Customer 1 Shopping ===");
        Product laptop = productService.findByTitle("Laptop");
        Product mouse = productService.findByTitle("Mouse");
        cart1.add(laptop);
        cart1.add(mouse);
        cart1.printCart();

        System.out.println("\n=== Customer 2 Shopping ===");
        Product keyboard = productService.findByTitle("Keyboard");
        Product monitor = productService.findByTitle("Monitor");
        cart2.add(keyboard);
        cart2.add(monitor);
        cart2.printCart();

        orderService.createOrder(cart1);
        orderService.createOrder(cart2);

        System.out.println("\n=== Cart Status After Orders ===");
        cart1.printCart();
        cart2.printCart();

        System.out.println("\n=== Search Product ===");
        Product found = productService.findByTitle("Webcam");
        System.out.println("Found: " + found);

        ((AnnotationConfigApplicationContext) context).close();
    }
}

/*
ProductService initialized with 10 products
=== Welcome to Spring Shop ===

=== All Products ===
Product{id=1, title='Laptop', cost=1200.0}
Product{id=2, title='Mouse', cost=25.99}
Product{id=3, title='Keyboard', cost=89.99}
Product{id=4, title='Monitor', cost=350.0}
Product{id=5, title='Headphones', cost=79.99}
Product{id=6, title='Webcam', cost=129.99}
Product{id=7, title='USB Cable', cost=12.99}
Product{id=8, title='Desk', cost=299.99}
Product{id=9, title='Chair', cost=189.99}
Product{id=10, title='Mousepad', cost=9.99}

=== Customer 1 Shopping ===
Added: Laptop (cost: 1200.0)
Added: Mouse (cost: 25.99)

=== Shopping Cart ===
Cart is empty

=== Customer 2 Shopping ===
Added: Keyboard (cost: 89.99)
Added: Monitor (cost: 350.0)

=== Shopping Cart ===
Cart is empty
Cannot create order: cart is empty!
Cannot create order: cart is empty!

=== Cart Status After Orders ===

=== Shopping Cart ===
Cart is empty

=== Shopping Cart ===
Cart is empty

=== Search Product ===
Found: Product{id=6, title='Webcam', cost=129.99}
 */