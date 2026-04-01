package com.geekbrains.lesson9;

import java.sql.*;

public class MainAppHW {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();

            System.out.println("1. Table creation:");
            String createSQL = EntityProcessor.generateCreateTable(User.class);
            System.out.println("SQL: " + createSQL);
            statement.executeUpdate(createSQL);
            System.out.println("Table successfully created!\n");

            System.out.println("2. Data insertion.");
            User user1 = new User(1, "john_doe", 25, "john@example.com");
            User user2 = new User(2, "jane_smith", 30, "jane@example.com");
            User user3 = new User(3, "bob_wilson", 28, "bob@example.com");

            EntityProcessor.insertToDatabase(user1, connection);
            EntityProcessor.insertToDatabase(user2, connection);
            EntityProcessor.insertToDatabase(user3, connection);

            System.out.println("\n3. Reading data from DB:");
            readAllUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            statement = connection.createStatement();
            System.out.println("Connected to DB\n");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Driver not found");
        }
    }

    private static void readAllUsers() {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                System.out.printf("id: %d, name: %s, age: %d, email: %s%n",
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e.getMessage());
        }
    }

    public static void disconnect() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("\nConnection to DB closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
Connected to DB

1. Table creation:
SQL: CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT, age INTEGER, email TEXT);
Table successfully created!

2. Data insertion:

3. Reading data from DB:
id: 1, name: john_doe, age: 25, email: john@example.com
id: 2, name: jane_smith, age: 30, email: jane@example.com
id: 3, name: bob_wilson, age: 28, email: bob@example.com

Connection to DB closed
 */