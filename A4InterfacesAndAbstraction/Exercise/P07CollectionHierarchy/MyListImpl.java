package A4InterfacesAndAbstraction.Exercise.P07CollectionHierarchy;

public class MyListImpl extends Collection implements MyList {

    @Override
    public String remove() {
        String removedItem = super.items.get(0);
        super.items.remove(removedItem);
        return removedItem;
    }

    @Override
    public int add(String item) {
        super.items.add(0, item);
        return 0;
    }

    @Override
    public int getUsed() {
        return super.items.size();
    }
}
