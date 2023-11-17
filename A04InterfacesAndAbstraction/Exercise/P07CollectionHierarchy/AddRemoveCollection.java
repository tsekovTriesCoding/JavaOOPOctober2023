package A04InterfacesAndAbstraction.Exercise.P07CollectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public String remove() {
        String removedItem = super.items.get(super.items.size() - 1);
        super.items.remove(removedItem);
        return removedItem;
    }

    @Override
    public int add(String item) {
        super.items.add(0, item);
        return 0;
    }
}
