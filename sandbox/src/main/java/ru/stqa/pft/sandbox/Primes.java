package ru.stqa.pft.sandbox;

public class Primes {
    public static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeWhile(int number) {
        int i = 2;
        while (i < number && number % i != 0) {
            i++;
        }
        return number == i;
    }
}
