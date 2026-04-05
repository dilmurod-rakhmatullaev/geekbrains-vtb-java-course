package com.geekbrains.lesson11.practice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MainApp {
    private static SessionFactory factory;
    private static CustomerDAO customerDAO;
    private static ProductDAO productDAO;
    private static PurchaseDAO purchaseDAO;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();

        customerDAO = new CustomerDAO(factory);
        productDAO = new ProductDAO(factory);
        purchaseDAO = new PurchaseDAO(factory);

        System.out.println("Allowed commands:");
        System.out.println("/showProductsByPerson <name>");
        System.out.println("/findPersonsByProductTitle <product_name>");
        System.out.println("/removePerson <name>");
        System.out.println("/removeProduct <product_name>");
        System.out.println("/buy <name> <product_name>");
        System.out.println("/exit");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            if (input.equals("/exit")) break;

            String[] parts = input.split(" ", 3);
            switch (parts[0]) {
                case "/showProductsByPerson":
                    if (parts.length < 2) {
                        System.out.println("Usage: /showProductsByPerson <name>");
                        break;
                    }
                    showProductsByPerson(parts[1]);
                    break;
                case "/findPersonsByProductTitle":
                    if (parts.length < 2) {
                        System.out.println("Usage: /findPersonsByProductTitle <product_name>");
                        break;
                    }
                    findPersonsByProductTitle(parts[1]);
                    break;
                case "/removePerson":
                    if (parts.length < 2) {
                        System.out.println("Usage: /removePerson <name>");
                        break;
                    }
                    removePerson(parts[1]);
                    break;
                case "/removeProduct":
                    if (parts.length < 2) {
                        System.out.println("Usage: /removeProduct <product_name>");
                        break;
                    }
                    removeProduct(parts[1]);
                    break;
                case "/buy":
                    if (parts.length < 3) {
                        System.out.println("Usage: /buy <name> <product_name>");
                        break;
                    }
                    buy(parts[1], parts[2]);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }

        factory.close();
        scanner.close();
    }

    private static void showProductsByPerson(String customerName) {
        Customer customer = customerDAO.findByNameWithPurchases(customerName);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }

        System.out.println("Purchased products by " + customerName + ":");
        for (Purchase p : customer.getPurchases()) {
            System.out.println(" - " + p.getProduct().getTitle() +
                    " (price: " + p.getPurchasePrice() + ")");
        }
    }

    private static void findPersonsByProductTitle(String productTitle) {
        Product product = productDAO.findByTitleWithPurchases(productTitle);
        if (product == null) {
            System.out.println("Product not found");
            return;
        }

        System.out.println("Customers who purchased " + productTitle + ":");
        for (Purchase p : product.getPurchases()) {
            System.out.println(" - " + p.getCustomer().getName());
        }
    }

    private static void removePerson(String customerName) {
        Customer customer = customerDAO.findByName(customerName);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        customerDAO.delete(customer);
        System.out.println("Customer " + customerName + " deleted");
    }

    private static void removeProduct(String productTitle) {
        Product product = productDAO.findByTitle(productTitle);
        if (product == null) {
            System.out.println("Product not found");
            return;
        }
        productDAO.delete(product);
        System.out.println("Product " + productTitle + " deleted");
    }

    private static void buy(String customerName, String productTitle) {
        Customer customer = customerDAO.findByName(customerName);
        if (customer == null) {
            customer = new Customer();
            customer.setName(customerName);
            customerDAO.save(customer);
        }

        Product product = productDAO.findByTitle(productTitle);
        if (product == null) {
            System.out.println("Product not found. Please add the product via the database first");
            return;
        }

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setPurchasePrice(product.getPrice());

        purchaseDAO.save(purchase);
        System.out.println(customerName + " purchased " + productTitle +
                " for " + product.getPrice());
    }
}
/*
> /buy John Laptop
    Hibernate: select ... from customer where name=?
    Hibernate: select ... from products where title=?
    Hibernate: insert into purchases ...
    John purchased Laptop for 1000

    > /showProductsByPerson John
    Hibernate: select distinct c1_0.id ... left join purchases ... left join products ...
    Purchased products by John:
     - Laptop (price: 1000)

    > /findPersonsByProductTitle Laptop
    Customers who purchased Laptop:
     - John

    > /removePerson John
    Customer John deleted

    > /showProductsByPerson John
    Customer not found
    */