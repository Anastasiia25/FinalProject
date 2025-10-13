package org.example;

import java.util.Scanner;
import java.util.Random;

public class ExpressionJUnit {

    private static final Random random = new Random();

    public static double calculateAverage(int userNumber) {
        if (userNumber <= 0) {

            throw new IllegalArgumentException("Input must be positive.");
        }

        int randomNumberSum = 0;
        for (int i = 0; i < userNumber; i++) {

            randomNumberSum += random.nextInt(100) + 1;
        }

        return (double) randomNumberSum / userNumber;
    }

     public static void main(String[] args) {

        Scanner input =new Scanner (System.in);
        int user_number;
        do {

            System.out.print("\n\nEnter a number (0 to exit): ");

            user_number = input.nextInt();
            int random_number = 0;
            if (user_number == 0)
                break;

            for (int i = 0; i > user_number; i++)
                random_number += (int) (Math.random() * 100 + 1);

            System.out.println("\n\n1. User number: " + user_number
                    + "\n\n2. Random number: " + random_number + "\n\nThe result of expression (random number/user number) is: " +
                    ( (double) random_number / user_number));

        } while (true);
        input.close();
    }

}