package A01WorkingWithAbstractions.Exercise.P06GreedyTimes;

import java.util.*;

public class Bag {
    private long capacity;
    private List<Item> items;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public void putItem(Item itemToAdd) {
        if (canAddItem(itemToAdd)) {
            if (itemExists(itemToAdd)) {
                Item item1 = this.items.stream()
                        .filter(i -> i.getItemName().equals(itemToAdd.getItemName())).findFirst()
                        .get();
                item1.setAmount(item1.getAmount() + itemToAdd.getAmount());
            } else {
                this.items.add(itemToAdd);
            }
        }
    }

    private boolean itemExists(Item itemToAdd) {
        for (Item item : this.items) {
            if (item.getItemName().equals(itemToAdd.getItemName())) {
                return true;
            }
        }
        return false;
    }

    public boolean canAddItem(Item item) {
        if (this.capacity < getTotalAmount() + item.getAmount()) {
            return false;
        }

        switch (item.getItemType()) {
            case Gem:
                if (getTotalAmountByType(ItemType.Gem) + item.getAmount() > getTotalAmountByType(ItemType.Gold)) {
                    return false;
                }
                break;
            case Cash:
                if (getTotalAmountByType(ItemType.Cash) + item.getAmount() > getTotalAmountByType(ItemType.Gem)) {
                    return false;
                }
                break;
        }

        return true;
    }

    public long getTotalAmount() {
        return getTotalAmountByType(ItemType.Cash) + getTotalAmountByType(ItemType.Gem) + getTotalAmountByType(ItemType.Gold);
    }

    private Long getTotalAmountByType(ItemType itemType) {
        return this.items.stream()
                .filter(item -> item.getItemType().equals(itemType))
                .mapToLong(Item::getAmount)
                .sum();
    }

    private Map<ItemType, Long> getAllTypesTotalAmount() {
        Map<ItemType, Long> map = new HashMap<>();
        for (ItemType itemType : ItemType.values()) {
            map.put(itemType, getTotalAmountByType(itemType));
        }
        return map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getAllTypesTotalAmount().entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> {
                    sb.append(String.format("<%s> $%s", entry.getKey(), entry.getValue())).append(System.lineSeparator());
                    this.items.stream()
                            .filter(item -> item.getItemType().equals(entry.getKey()))
                            .sorted(Comparator.comparing(Item::getItemName).reversed()
                                    .thenComparingLong(Item::getAmount))
                            .forEach(item -> {
                                sb.append(String.format("##%s - %d", item.getItemName(), item.getAmount())).append(System.lineSeparator());
                            });
                });

        return sb.toString();
    }
}
