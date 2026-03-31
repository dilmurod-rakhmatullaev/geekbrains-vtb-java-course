package com.geekbrains.lesson8;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

// Reflection
public class MainApp {
    public static void main(String[] args) throws Exception {
        System.out.println(MainApp.class.getName());
        System.out.println(String.class);
        System.out.println(int.class);

        Class stringClass1 = "Java".getClass();
        Class stringClass2 = String.class;

        System.out.println(stringClass1);
        System.out.println(stringClass2);
        System.out.println("-//-");

        Class classCat = Cat.class;

        Method[] methods = classCat.getDeclaredMethods();
        for (Method o : methods) {
            System.out.println(o.getName());
        }

        System.out.println("-//-");

        Cat cat1 = new Cat(1, 2, 3);
        methods[0].invoke(cat1); // public meow
        methods[1].setAccessible(true);
        methods[1].invoke(cat1); // private meow

        int mods = methods[0].getModifiers();
        System.out.println("isStatic = " + Modifier.isStatic(mods));
        System.out.println("isFinal = " + Modifier.isFinal(mods));
        System.out.println("isPublic = " + Modifier.isPublic(mods));

        Field[] fields = classCat.getDeclaredFields();
        Cat cat2 = new Cat(1, 2, 3);
        System.out.println(Arrays.toString(fields));
        fields[1].set(cat2, 20);
        System.out.println(fields[1].get(cat2));

        System.out.println("-//-");

        Cat cat3 = (Cat) classCat.newInstance();
        System.out.println(cat3);

        Cat cat4 = (Cat) classCat.getConstructor(int.class, int.class, int.class)
                .newInstance(20, 30, 40);
        System.out.println(cat3);

        System.out.println("-//-");
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("C:\\Users\\SB\\BackendProjects\\GeekbrainsAndVTB\\lesson8-reflection\\target\\classes").toURI().toURL()});
            Class<?> humanClass = classLoader.loadClass("Human");
            Object humanObj = humanClass.getConstructor(String.class, int.class).newInstance("Bob", 30);
            Method greetingsMethod = humanClass.getDeclaredMethod("greetings");
            greetingsMethod.invoke(humanObj);   // Hi Bob
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.err.println("No such method: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Another exception: " + e.getMessage());
            e.printStackTrace();        }
    }
}
