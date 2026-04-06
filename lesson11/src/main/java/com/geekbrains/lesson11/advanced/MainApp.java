package com.geekbrains.lesson11.advanced;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static final int THREAD_COUNT = 8;
    private static final int OPERATIONS_PER_THREAD = 20_000;
    private static final int TOTAL_OPERATIONS = THREAD_COUNT * OPERATIONS_PER_THREAD;
    private static final int EXPECTED_SUM = TOTAL_OPERATIONS;  // 160,000

    public static void main(String[] args) throws InterruptedException {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();

        ItemService service = new ItemService(factory);

        service.initTable();

        System.out.println("Initial condition:");
        service.printAllItems();
        System.out.println("Initial sum: " + service.getTotalSum());
        System.out.println();

        // 3. Запуск потоков
        System.out.println("Launching " + THREAD_COUNT + " threads per " + OPERATIONS_PER_THREAD + " operations...");
        long startTime = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                ItemService threadService = new ItemService(factory);
                threadService.runTask(OPERATIONS_PER_THREAD);
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("All threads finished within " + (endTime - startTime) + " ms");
        System.out.println();

        System.out.println("=== RESULT CHECKING ===");
        service.printAllItems();

        int totalSum = service.getTotalSum();
        System.out.println();
        System.out.println("Total sum: " + totalSum);
        System.out.println("Expected sum: " + EXPECTED_SUM);
        System.out.println("Total operations: " + TOTAL_OPERATIONS);

        if (totalSum == EXPECTED_SUM) {
            System.out.println("✅ Test passed! Sum matches.");
        } else {
            System.out.println("❌ Test not passed! Lost " + (EXPECTED_SUM - totalSum) + " updates.");
        }

        factory.close();
    }
}
