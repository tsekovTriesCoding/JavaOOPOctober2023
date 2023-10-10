package A3Inheritance.Lab.P04StackOfStrings;

public class Main {
    public static void main(String[] args) {

        StackOfStrings stack = new StackOfStrings();
        stack.push("word");
        stack.push("some");
        stack.push("banana");
        stack.push("toy");
        stack.push("George");

        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        stack.pop();
        stack.pop();
        System.out.println(stack.pop());
    }
}
