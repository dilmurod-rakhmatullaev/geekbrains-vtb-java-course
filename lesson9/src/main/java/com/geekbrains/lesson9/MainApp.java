package com.geekbrains.lesson9;

import java.sql.*;

public class MainApp {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connect();
            clearTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void rollbackTable() throws SQLException {
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 80)");
        Savepoint savepoint1 = connection.setSavepoint();
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 80)");
        connection.rollback(savepoint1);
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 80)");
        connection.commit();
    }

    private static void batchTable() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "Bob" + (i + 1));
            preparedStatement.setInt(2, 50);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
    }

    private static void transactionAndPreparedStatement() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "Bob" + (i + 1));
            preparedStatement.setInt(2, 50);
            preparedStatement.executeUpdate();
        }
        connection.commit();    // AutoCommit (on = 261 ms; off 41368 ms)
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("DROP TABLE students");
    }

    private static void clearTable() throws SQLException {
        statement.executeUpdate("DELETE FROM students");
    }

    private static void deleteStudent() throws SQLException {
        statement.executeUpdate("DELETE FROM students WHERE id = 3");
    }

    private static void updateStudent() throws SQLException {
        statement.executeUpdate("UPDATE students SET score = 80 WHERE id = 2");
    }

    private static void selectStudent() {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM students;")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name") + " " + resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent() throws SQLException {
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Kyle', 22);");
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}