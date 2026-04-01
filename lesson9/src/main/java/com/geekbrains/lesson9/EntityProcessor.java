package com.geekbrains.lesson9;

import javax.net.ssl.SSLProtocolException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntityProcessor {
    public static String generateCreateTable(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " is not annotated with @Table");
        }
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name().isEmpty() ?
                clazz.getSimpleName().toLowerCase() :
                tableAnnotation.name();

        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sql.append(tableName).append(" (");

        Field[] fields = clazz.getDeclaredFields();
        List<String> columns = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name().isEmpty() ?
                        field.getName() :
                        column.name();

                String sqlType = getSqlType(field.getType());

                String columnDef = columnName + " " + sqlType;
                if (column.primaryKey()) {
                    columnDef += " PRIMARY KEY";
                }
                columns.add(columnDef);
            }
        }

        sql.append(String.join(", ", columns));
        sql.append(");");

        return sql.toString();
    }

    public static String generateInsert(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class is not annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name().isEmpty() ?
                clazz.getSimpleName().toLowerCase() :
                tableAnnotation.name();

        List<String> columnNames = new ArrayList<>();
        List<String> values = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name().isEmpty() ?
                        field.getName() :
                        column.name();

                field.setAccessible(true);
                Object value = field.get(obj);

                columnNames.add(columnName);
                values.add(formatValue(value));
            }
        }
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (").append(String.join(", ", columnNames)).append(")");
        sql.append(" VALUES (").append(String.join(", ", values)).append(");");

        return sql.toString();
    }
    private static String getSqlType(Class<?> type) {
        if (type == int.class || type == Integer.class) return "INTEGER";
        if (type == long.class || type == Long.class) return "BIGINT";
        if (type == String.class) return "TEXT";
        if (type == double.class || type == Double.class) return "REAL";
        if (type == boolean.class || type == Boolean.class) return "BOOLEAN";
        return "TEXT";
    }

    private static String formatValue(Object value) {
        if (value == null) return "NULL";
        if (value instanceof String) return "'" + value + "'";
        return value.toString();
    }

    public static void insertToDatabase(Object obj, Connection conn) {
        try {
            String sql = generateInsert(obj);
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
