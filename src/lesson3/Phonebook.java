package lesson3;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private String surname;
    private Integer numberPhone;

    Map<String, Integer> phonebook = new HashMap<>();

    void add(String surname, Integer numberPhone) {
        phonebook.put(surname,numberPhone);
    }

    void get(String surname) {
        for (Map.Entry<String, Integer> entry : phonebook.entrySet()) {
            if( surname.equals(entry.getKey()) ) {
                System.out.println("В телефонном справочнике найден: " + entry.getKey() + " - номер телефона: " + entry.getValue());
            }

        }
    }


}
