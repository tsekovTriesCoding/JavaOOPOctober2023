package A1WorkingWithAbstraction;

import java.util.Scanner;

public class P01RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            printRow(i, n - i);
        }

        for (int i = n - 1; i >= 1; i--) {
            printRow(i, n - i);
        }
    }

    private static void printRow(int numberOfStars, int numberOfSpaces) {
        for (int i = 1; i <= numberOfSpaces; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < numberOfStars; i++) {
            System.out.print("* ");
        }
        System.out.println();
    }

}
