package A02Encapsulation.Exercise.P01ClassBoxDataValidation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double length = Double.parseDouble(scanner.nextLine());
        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        Box box = new Box(length, width, height);

        System.out.printf("%.2f%n", box.calculateSurfaceArea());
        System.out.printf("%.2f%n", box.calculateLateralSurfaceArea());
        System.out.printf("%.2f", box.calculateVolume());
    }
}

