package A04InterfacesAndAbstraction.Exercise.P07CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] items = scanner.nextLine().split("\\s+");

        AddCollection addCollection = new AddCollection();
        performAddOperation(addCollection, items);


        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        performAddOperation(addRemoveCollection, items);

        MyListImpl myList = new MyListImpl();
        performAddOperation(myList, items);


        int removedOperations = Integer.parseInt(scanner.nextLine());

        performRemoveOperation(addRemoveCollection, removedOperations);

        performRemoveOperation(myList, removedOperations);

    }

    private static void performRemoveOperation(AddRemovable addRemovable, int removedOperations) {
        for (int i = 0; i < removedOperations; i++) {
            System.out.print(addRemovable.remove() + " ");
        }
        System.out.println();
    }

    private static void performAddOperation(Addable addable, String[] items) {
        for (String item : items) {
            System.out.print(addable.add(item) + " ");
        }
        System.out.println();
    }

}
