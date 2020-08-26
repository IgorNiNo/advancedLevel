package lesson3;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Задание 1
        String[] arr = {"ель", "вата", "арбуз", "ночь", "дом",
                "жук", "грабли", "игра", "колесо", "дом",
                "барабан", "жук", "игра", "колесо", "заяц",
                "дом", "леска", "мороз", "ночь"};

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);
        }
        System.out.println("Список уникальных слов, составляющих массив:");
        System.out.println(map.keySet());
        System.out.println("\nКоличество повторяющихся слов в массиве:");
        System.out.println(map);
        System.out.println();


        //Задание 2
        Phonebook surName = new Phonebook();

        surName.add("Иванов", 2223322);
        surName.add("Петров", 3002020);
        surName.add("Сидоров", 5556677);
        surName.add("Петров", 2005000);

        surName.get("Петров");

    }

}
