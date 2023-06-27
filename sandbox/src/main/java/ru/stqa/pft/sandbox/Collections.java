package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "C#", "Python", "JavaScript");

        for (String language : languages) {
            System.out.println("Я хочу выучить " + language);
        }
    }
}
