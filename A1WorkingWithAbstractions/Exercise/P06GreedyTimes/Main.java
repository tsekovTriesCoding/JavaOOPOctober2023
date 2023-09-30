
package A1WorkingWithAbstractions.Exercise.P06GreedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(bagCapacity);

        for (int i = 0; i < safe.length; i += 2) {
            String name = safe[i];
            long amount = Long.parseLong(safe[i + 1]);

            Item item = createItem(name, amount);

            bag.putItem(item);

        }

        System.out.println(bag);

    }

    private static Item createItem(String name, long quantity) {
        ItemType itemType;
        if (name.length() == 3) {
            itemType = ItemType.Cash;
        } else if (name.toLowerCase().endsWith("gem")) {
            itemType = ItemType.Gem;
        } else if (name.toLowerCase().equals("gold")) {
            itemType = ItemType.Gold;
        } else {
            return null;
        }

        return new Item(itemType, name, quantity);
    }
}