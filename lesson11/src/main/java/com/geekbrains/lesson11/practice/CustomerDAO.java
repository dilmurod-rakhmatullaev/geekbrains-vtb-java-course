package com.geekbrains.lesson11.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomerDAO {
    private SessionFactory factory;

    public CustomerDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Customer findByNameWithPurchases(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createQuery(
                            "select distinct c from Customer c " +
                                    "left join fetch c.purchases p " +
                                    "left join fetch p.product " +
                                    "where c.name = :name",
                            Customer.class)
                    .setParameter("name", name)
                    .uniqueResult();
            session.getTransaction().commit();
            return customer;
        }
    }

    public Customer findByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.createQuery(
                            "from Customer where name = :name", Customer.class)
                    .setParameter("name", name)
                    .uniqueResult();
            session.getTransaction().commit();
            return customer;
        }
    }

    public void save(Customer customer) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
        }
    }

    public void delete(Customer customer) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.remove(customer);
            session.getTransaction().commit();
        }
    }
}