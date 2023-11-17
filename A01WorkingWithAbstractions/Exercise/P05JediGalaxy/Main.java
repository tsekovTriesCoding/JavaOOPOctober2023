package A01WorkingWithAbstractions.Exercise.P05JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] galaxyMatrix = initialiseMatrix(scanner);

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] jediCoordinates = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            moveEvilAndDestroyStars(galaxyMatrix, evilCoordinates);
            sum += moveJediAndCalculateStars(galaxyMatrix, jediCoordinates);

            command = scanner.nextLine();
        }

        System.out.println(sum);

    }

    private static long moveJediAndCalculateStars(int[][] galaxyMatrix, int[] jediCoordinates) {
        int jediRow = jediCoordinates[0];
        int jediCol = jediCoordinates[1];
        long sum = 0;
        while (jediCanStillMove(galaxyMatrix, jediRow, jediCol)) {
            if (jediRow >= 0 && jediRow < galaxyMatrix.length && jediCol >= 0 && jediCol < galaxyMatrix[0].length) {
                sum += galaxyMatrix[jediRow][jediCol];
            }
            jediRow--;
            jediCol++;
        }
        return sum;
    }

    private static boolean jediCanStillMove(int[][] galaxyMatrix, int jediRow, int jediCol) {
        return jediRow >= 0 && jediCol < galaxyMatrix[1].length;
    }

    private static void moveEvilAndDestroyStars(int[][] galaxyMatrix, int[] evilCoordinates) {
        int evilRow = evilCoordinates[0];
        int evilCol = evilCoordinates[1];

        while (evilCanStillMove(evilRow, evilCol)) {
            if (evilRow >= 0 && evilRow < galaxyMatrix.length && evilCol >= 0 && evilCol < galaxyMatrix[0].length) {
                galaxyMatrix[evilRow][evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }
    }

    private static boolean evilCanStillMove(int evilRow, int evilCol) {
        return evilRow >= 0 && evilCol >= 0;
    }

    private static int[][] initialiseMatrix(Scanner scanner) {
        int[] dimensions = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = value++;
            }
        }
        return matrix;
    }
}
