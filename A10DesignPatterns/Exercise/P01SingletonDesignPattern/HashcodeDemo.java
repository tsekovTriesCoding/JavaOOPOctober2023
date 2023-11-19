package A10DesignPatterns.Exercise.P01SingletonDesignPattern;

public class HashcodeDemo {

    public static void main(String[] args) {

        Hashcode hashcode = Hashcode.getInstance("SomePoint");
        System.out.println(hashcode.getPoint().hashCode());

        Hashcode hashcode2 = Hashcode.getInstance("NewPoint");
        System.out.println(hashcode2.getPoint().hashCode());
    }
}
