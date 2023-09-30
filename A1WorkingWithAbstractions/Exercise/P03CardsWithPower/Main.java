package A1WorkingWithAbstraction.Exercise.P03CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Card.CardRank cardRank = Card.CardRank.valueOf(scanner.nextLine());
        Card.CardSuit cardSuit = Card.CardSuit.valueOf(scanner.nextLine());

        Card card = new Card(cardRank, cardSuit);

        System.out.printf("Card name: %s of %s; Card power: %d", cardRank, cardSuit, card.getPower());
    }
}
