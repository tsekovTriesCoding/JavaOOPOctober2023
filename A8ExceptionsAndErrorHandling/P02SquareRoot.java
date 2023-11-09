package A8ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P02SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        try {
            double numberSqrt = getSqrt(Integer.parseInt(input));
            System.out.printf("%.2f%n", numberSqrt);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid");
        } finally {
            System.out.println("Goodbye");
        }
    }

    public static double getSqrt(int number) {
        if (number > 0) {
            return Math.sqrt(number);
        }

        throw new IllegalArgumentException();
    }
}
