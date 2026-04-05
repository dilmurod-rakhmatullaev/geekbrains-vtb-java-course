package com.geekbrains.lesson11.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductDAO {
    private SessionFactory factory;

    public ProductDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Product findByTitle(String title) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createQuery(
                            "from Product where title = :title", Product.class)
                    .setParameter("title", title)
                    .uniqueResult();
            session.getTransaction().commit();
            return product;
        }
    }
    public Product findByTitleWithPurchases(String title) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createQuery(
                            "select distinct p from Product p " +
                                    "left join fetch p.purchases pur " +
                                    "left join fetch pur.customer " +
                                    "where p.title = :title",
                            Product.class)
                    .setParameter("title", title)
                    .uniqueResult();
            session.getTransaction().commit();
            return product;
        }
    }

    public void save(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
        }
    }

    public void delete(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.remove(product);
            session.getTransaction().commit();
        }
    }
}