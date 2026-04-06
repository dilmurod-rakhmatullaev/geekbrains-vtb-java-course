package com.geekbrains.lesson11.advanced;

import org.hibernate.SessionFactory;

public class ItemService {
    private ItemDAO itemDAO;
    private SessionFactory factory;

    public ItemService(SessionFactory factory) {
        this.factory = factory;
        this.itemDAO = new ItemDAO(factory);
    }

    public void performUpdate() {
        // ВАЖНО: вся операция в одной транзакции!
        try (org.hibernate.Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            int randomId = (int) (Math.random() * 40) + 1;
            Item item = session.createQuery(
                            "FROM Item WHERE id = :id", Item.class)
                    .setParameter("id", (long) randomId)
                    .setLockMode(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
                    .uniqueResult();

            if (item == null) {
                session.getTransaction().rollback();
                return;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                session.getTransaction().rollback();
                return;
            }

            item.setVal(item.getVal() + 1);
            session.merge(item);

            session.getTransaction().commit();
        }
    }

    public void runTask(int operationsCount) {
        for (int i = 0; i < operationsCount; i++) {
            performUpdate();
        }
    }

    public void initTable() {
        itemDAO.initTable();
    }

    public int getTotalSum() {
        return itemDAO.getTotalSum();
    }

    public void printAllItems() {
        itemDAO.printAllItems();
    }
}
