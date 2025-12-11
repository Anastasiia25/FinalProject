package org.example;

import java.util.Scanner;
import java.util.Random;

public class ExpressionJUnit {

    private static final Random random = new Random();

    public static int calculateRandomSum(int userNumber) {
        if (userNumber <= 0) {
            
            throw new IllegalArgumentException("Input must be a positive number."); 
        }

        int randomNumberSum = 0;
        for (int i = 0; i < userNumber; i++) {
            
            randomNumberSum += random.nextInt(100) + 1; 
        }
        return randomNumberSum;


    }



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int user_number;

        do {
            System.out.print("\n\nEnter a number (0 to exit): ");

            if (!input.hasNextInt()) {
                System.out.println("Error! Invalid input type.");
                input.next();
                continue;
            }

            user_number = input.nextInt();

            if (user_number == 0)
                break;

            try {

                int sum = calculateRandomSum(user_number);

                double result = (double) sum / user_number;

                System.out.println("\n\n1. User number: " + user_number

                        + "\n\n3. Sum of Random numbers: " + sum
                        + "\n\nThe final result is: " + result);

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }

        } while (true);

        input.close();
        System.out.println("Application exited successfully.");
    }
}