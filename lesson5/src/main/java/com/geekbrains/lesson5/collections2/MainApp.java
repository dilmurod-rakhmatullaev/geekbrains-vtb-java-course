package com.geekbrains.lesson5.collections2;

import java.util.*;

public class MainApp {
      public static void main(String[] args) {
            Map<String, String> map = new HashMap<>();
            map.put("A", "B");
            map.put("C", "D");

            for (Map.Entry<String, String> o : map.entrySet()) {
                  System.out.println("Key: " + o.getKey() + " and value: " + o.getValue());
            }

            System.out.println(map.getOrDefault("A", "Q"));   // returns "B"
            System.out.println(map.getOrDefault("E", "F"));   // returns "F"


            Set<String> set = new HashSet<>();
            set.add("A");
            set.add("A");
            set.add("A");
            set.add("B");

            System.out.println(set.size());     // returns 2

            List<String> list1 = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "A"));
            Iterator<String> iter = list1.iterator();
            while (iter.hasNext()) {
                  String o = iter.next();
                  if (o.equals("A")) {
                        iter.remove();
                  }
            }
            System.out.println(list1);     // returns [B, C]

            List<String> list2 = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "A"));
            for (int i = 0; i < list2.size(); i++) {
                  if (list2.get(i).equals("A")) {
                        list2.remove(i);
                  }
            }
            System.out.println(list2);    // returns [A, B, C]


            Set<String> set2 = new HashSet<>();

            set2.add("A");
            set2.add("AAAA");
            set2.add("AAA");
            set2.add("AA");
            Iterator<String> iter2 = set2.iterator();
            while (iter2.hasNext()) {
                  String o = iter2.next();
                  if (o.length() > 1) {
                        iter2.remove();
                  }
            }
            System.out.println(set2);     // returns [A]
      }
}
