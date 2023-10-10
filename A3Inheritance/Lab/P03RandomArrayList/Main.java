package A3Inheritance.Lab.P03RandomArrayList;

public class Main {
    public static void main(String[] args) {

        RandomArrayList<Integer> randomArrayList = new RandomArrayList<>();

        randomArrayList.add(5);
        randomArrayList.add(10);
        randomArrayList.add(54);
        randomArrayList.add(92);

        randomArrayList.remove(0);
        System.out.println(randomArrayList.getRandomElement());
    }
}
