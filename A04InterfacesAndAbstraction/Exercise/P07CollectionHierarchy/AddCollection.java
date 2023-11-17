package A04InterfacesAndAbstraction.Exercise.P07CollectionHierarchy;

public class AddCollection extends Collection implements Addable {

    public int add(String item) {
        super.items.add(item);
        return super.items.size() - 1;
    }
}
