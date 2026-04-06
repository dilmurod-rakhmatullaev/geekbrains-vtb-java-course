package com.geekbrains.lesson11.advanced;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAO {
    private SessionFactory factory;

    public ItemDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void initTable() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            session.createMutationQuery("DELETE FROM Item").executeUpdate();

            for (int i = 0; i < 40; i++) {
                session.persist(new Item(0));
            }

            session.getTransaction().commit();
            System.out.println("Table initialized: 40 rows with 0 value");
        }
    }

    public Item getRandomItemWithLock() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            int randomId = (int) (Math.random() * 40) + 1;

            Item item = session.createQuery(
                    "FROM Item WHERE id = :id", Item.class)
                            .setParameter("id", (long) randomId)
                            .setLockMode(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
                            .uniqueResult();

            session.getTransaction().commit();
            return item;
        }
    }

    public void incrementValue(Item item) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            item.setVal(item.getVal() + 1);
            session.merge(item);

            session.getTransaction().commit();
        }
    }

    public int getTotalSum() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Query<Long> query = session.createQuery(
                    "SELECT SUM(val) FROM Item", Long.class);
            Long sum = query.uniqueResult();

            session.getTransaction().commit();
            return sum != null ? sum.intValue() : 0;
        }
    }

    public void printAllItems() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<Item> items = session.createQuery("FROM Item ORDER BY id", Item.class).list();

            for (Item item : items) {
                System.out.println(item);
            }

            session.getTransaction().commit();
        }
    }
}
