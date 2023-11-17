package A01WorkingWithAbstractions.Exercise.P06GreedyTimes;

public class Item {
    private ItemType itemType;
    private String ItemName;
    private long amount;

    public Item(ItemType itemType, String itemName, long amount) {
        this.itemType = itemType;
        ItemName = itemName;
        this.amount = amount;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getItemName() {
        return ItemName;
    }


    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
