package p03_IteratorTest;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        String[] tokens = command.split("\\s+");
        ListIterator listIterator = new ListIterator();
        if (tokens.length > 1) {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
            listIterator = new ListIterator(tokens);
        }

        command = scanner.nextLine();
        while (!command.equals("END")) {
            switch (command) {
                case "Move":
                    System.out.println(listIterator.move());
                    break;
                case "HasNext":
                    System.out.println(listIterator.hasNext());
                    break;
                case "Print":
                    try {
                        System.out.println(listIterator.print());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

            command = scanner.nextLine();
        }
    }
}
