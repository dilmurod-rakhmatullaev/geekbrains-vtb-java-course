package com.geekbrains.lesson11.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PurchaseDAO {
    private SessionFactory factory;

    public PurchaseDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void save(Purchase purchase) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(purchase);
            session.getTransaction().commit();
        }
    }
}